package server.sources.controllers;

import server.sources.interfaces.*;
import server.sources.models.Market;
import server.sources.models.Player;
import server.sources.notifications.EndOfGameNotification;
import server.sources.notifications.GameStartedNotification;
import server.sources.notifications.MessageNotification;
import server.sources.notifications.RestPlayerNotification;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameController extends UnicastRemoteObject implements GameControllerInterface, Runnable {

    private static final long serialVersionUID = 1337L;

    private enum GameStates { LOBBY, STARTED, RUNNING, ENDED }

    public ArrayList<Player> players = new ArrayList<Player>();

    public transient ServerInterface server;
    private GameStates gameState = GameStates.LOBBY;

    private final int MAX_ROUNDS = 7;

    private int round = 0;
    private int turn = 0;

    private StoryController stories = new StoryController();
    private Market market = new Market();
    private ReputationBoardController reputationboard = new ReputationBoardController();

    public GameController(ServerInterface server) throws RemoteException {
        this.server = server;

        // Load the market
        this.market.load();

        // Load the stories
        this.stories.load();
    }

    public void play()throws RemoteException {

        this.startGame();

        this.runGame();

        this.endGame();

    }

    private void startGame() throws RemoteException {
        this.setGameState(GameStates.STARTED);

        // Send everyone to the main screen
        System.out.println("[Game] Started");
        this.server.notifyClients(new GameStartedNotification());

    }

    public void runGame() throws RemoteException {
        this.setGameState(GameStates.RUNNING);

        while (!this.gameHasEnded()) {

            do {
                Player player = players.get((this.round + this.turn) % players.size());

                if (!player.hasPassed()) {

                    try {
                        player.requestAction();
                        while (!player.hasAction()) {
                            Thread.sleep(100);
                        }

                        System.out.println("Execute action");
                        this.server.executeAction(player.getAction());

                    } catch (RemoteException | InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                this.turn++;
            } while(!this.roundHasEnded());

            this.restVillagers();

            this.turn = 0;
            this.round++;

            this.server.notifyClients(new MessageNotification("u all gay"));
        }

    }

    private void restVillagers() throws RemoteException {
        boolean hasAction = true;

        for (Player player : players) {
            player.resetAfterRound();
            player.getGameClient().receiveNotification(new RestPlayerNotification());
            player.requestAction();
        }

        do {

            hasAction = true;

            try {

                for (Player player : players) {
                    System.out.println("REEEE");


                    System.out.println("player.hasAction() = " + player.hasAction());

                    if (!player.hasAction()) hasAction = false;


                    System.out.println("hasAction = " + hasAction);
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("hasAction = " + hasAction);

        } while (!hasAction);

    }

    private void endGame() throws RemoteException {
        this.setGameState(GameStates.ENDED);

        this.server.notifyClients(new EndOfGameNotification());

        System.out.println("[Game] Ended");

    }

    @Override
    public void run() {

        try {
            this.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void removePlayer(GameClientInterface gameClient) throws RemoteException {
        if (gameClient.getPlayer() == null) return;

        for (int i = 0; i < this.players.size(); i++) {
            Player player = this.players.get(i);

            if (player.getGameClient().equals(gameClient)) {
                this.players.remove(i);
            }
        }

    }

    private boolean roundHasEnded() {
        boolean hasEnded = true;

        for (Player player : this.players) {
            if (!player.hasPassed()) hasEnded = false;
        }

        return hasEnded;
    }

    private boolean gameHasEnded() {
        return this.round >= this.MAX_ROUNDS;
    }

    private void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    @Override
    public ArrayList<PlayerInterface> listCurrentPlayers() throws RemoteException {
        // Im casting some black magic right here
        return (ArrayList<PlayerInterface>) (ArrayList<?>) this.players;

    }

    @Override
    public StoryControllerInterface getStories() throws RemoteException {
        return (StoryControllerInterface) stories;
    }

    @Override
    public MarketInterface getMarket() throws RemoteException {
        return (MarketInterface) market;
    }

    public ReputationBoardInterface getReputationBoard(){
        return (ReputationBoardInterface) reputationboard;
    }
}
