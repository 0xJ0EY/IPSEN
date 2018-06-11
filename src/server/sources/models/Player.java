package server.sources.models;

import server.sources.controllers.GameController;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.notifications.PlayerTurnNotification;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Player extends UnicastRemoteObject implements PlayerInterface {

    private static final long serialVersionUID = 1337L;

    private PlayerBoard board = new PlayerBoard(this);

    private GameController gameController;
    private int reputation = 0;

    private transient GameClientInterface gameClient;

    private String colour = "";

    private ActionInterface action = null;

    private boolean passed = false;

    private String username;

    public Player(String username) throws RemoteException {
        this.colour = UUID.randomUUID().toString();
        this.username = username;
    }

    public GameClientInterface getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;

        // Update the playerview, so it will be shown in the observer
        this.board.updateObserver();
    }

    public boolean hasPassed() {
        return this.passed;
    }

    public void pass() {
        this.passed = true;
    }

    public void requestAction() throws RemoteException {
        this.action = null;
        gameController.server.notifyClients(new PlayerTurnNotification(this.gameClient));
    }

    public ActionInterface getAction() {
        return this.action;
    }

    public boolean hasAction() {
        return this.action != null;
    }

    /**
     * Send action to the server and wait for the execute
     * @param action
     */
    public void doAction(ActionInterface action) {
        this.action = action;
    }

    public String getUsername() throws RemoteException {
        return this.username;
    }

    /**
     * Return the PlayerBoardInterface so it can be used for RMI communicaton
     * @return
     */
    @Override
    public PlayerBoardInterface getPlayerBoard() {
        return (PlayerBoard) this.board;
    }

    @Override
    public int getReputation() throws RemoteException {
        return this.reputation;
    }

    public void resetAfterRound() {
        this.passed = false;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public void changeReputation(int amount) throws RemoteException {
        this.reputation += amount;
    }

}
