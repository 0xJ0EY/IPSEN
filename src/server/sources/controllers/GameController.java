package server.sources.controllers;

import client.source.controllers.BelowController;
import client.source.observers.Observable;
import server.sources.interfaces.*;
import server.sources.models.Market;
import server.sources.models.Player;
import server.sources.notifications.*;

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
    private Market market = new Market(this);
    private ReputationBoardController reputationboard = new ReputationBoardController();

    public GameController(ServerInterface server) throws RemoteException {
        this.server = server;

        // Load the market
        this.market.load();

        // Load the stories
        this.stories.load();
    }

    public void play() throws RemoteException {

        this.startGame();

        this.runGame();

        this.endGame();

    }

    private void startGame() throws RemoteException {
        this.setGameState(GameStates.STARTED);

        // Update the playerboard
        for (Player player : this.players) {
            player.getGameClient().receiveNotification(
                new UpdatePlayerBoardNotification(player.getPlayerBoard()
            ));
        }

        // Send everyone to the main screen
        System.out.println("[Game] Started");
        this.server.notifyClients(new GameStartedNotification());

        // Notify clients of the market / reputation board
        this.server.notifyClients(new MarketUpdateNotification(this.market));
        this.server.notifyClients(new GameControllerUpdateNotifcation(this));
    }

    public void runGame() throws RemoteException {
        this.setGameState(GameStates.RUNNING);

        while (true) {

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

            if (this.gameHasEnded()) { break; }

            this.endOfRound();
        }

    }

    /**
     * Request and then poll for all the players to rest thier villagers
     * @author Joey de Ruiter
     * @throws RemoteException
     */
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
                    if (!player.hasAction()) hasAction = false;
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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

    /**
     * Increment round counter
     * Reset turn indicator
     * Reset players and add rewards, etc ...
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    private void endOfRound() throws RemoteException {
        this.restVillagers();

        // Reset turn
        this.turn = 0;

        // Update round marker
        this.round++;

        // Reset villagers, so they can sleep again
        for (Player player : this.players) {
            player.getPlayerBoard().endOfRound();
        }

        this.updateObserver();
    }

    private boolean gameHasEnded() {
        return this.round >= this.MAX_ROUNDS;
    }

    private void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    /**
     * Return the story object so the StoryAction can generate stories
     *
     * @author Richard Kerkvliet
     * @return StoryControllerInterface
     * @throws RemoteException
     */
    @Override
    public StoryControllerInterface getStories() throws RemoteException {
        return (StoryControllerInterface) stories;
    }

    /**
     * Return the market interface so we can buy stuff.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException
     */
    @Override
    public MarketInterface getMarket() throws RemoteException {
        return (MarketInterface) market;
    }

    /**
     * Return the reputation board, this will keep track of the cider and the reputation.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException
     */
    public ReputationBoardInterface getReputationBoard(){
        return (ReputationBoardInterface) reputationboard;
    }

    @Override
    public int getCurrentRound() throws RemoteException {
        return this.round;
    }

    public void updateObserver() {
        try {
            this.server.notifyClients(new GameControllerUpdateNotifcation(this));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
