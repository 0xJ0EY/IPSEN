package server.sources;

import server.sources.interfaces.*;
import server.sources.controllers.MarketController;
import server.sources.models.Player;
import server.sources.controllers.StoryController;
import server.sources.notifications.EndOfGameNotification;
import server.sources.notifications.GameStartedNotification;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Game extends UnicastRemoteObject implements GameInterface, Runnable {

    private enum GameStates { LOBBY, STARTED, RUNNING, ENDED }

    public ArrayList<Player> players = new ArrayList<Player>();

    public ServerInterface server;
    private GameStates gameState = GameStates.LOBBY;

    private final int MAX_ROUNDS = 7;

    private int round = 0;
    private int turn = 0;

    private StoryController stories = new StoryController();
    private MarketController market = new MarketController();

    public Game(ServerInterface server) throws RemoteException {
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
        System.out.println("Send notification");
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

            for (Player player : this.players) {
                player.resetAfterRound();
            }

            this.turn = 0;
            this.round++;
        }

    }

    private void endGame() throws RemoteException {
        this.setGameState(GameStates.ENDED);

        this.server.notifyClients(new EndOfGameNotification());

        System.out.println("Game ended");

    }

    @Override
    public void run() {

        try {
            this.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void removePlayer(GameClientInterface gameClient) {

        for (Player player : this.players) {
            if (player.equals(gameClient)) {
                this.players.remove(player);
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
    public MarketControllerInterface getMarket() throws RemoteException {
        return (MarketControllerInterface) market;
    }
}
