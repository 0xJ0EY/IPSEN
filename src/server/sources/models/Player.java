package server.sources.models;

import server.sources.controllers.GameControllerController;
import server.sources.controllers.PlayerBoardController;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.notifications.PlayerTurnNotification;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Player extends UnicastRemoteObject implements PlayerInterface {

    public PlayerBoardController board = new PlayerBoardController();
    public GameControllerController gameController;

    private GameClientInterface gameClient;

    private ActionInterface action = null;

    private boolean passed = false;

    private String username;

    public Player(String username) throws RemoteException {
        this.username = username;
    }

    public GameClientInterface getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClientInterface gameClient) {
        this.gameClient = gameClient;
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
     * Return the PlayerBoardControllerInterface so it can be used for RMI communicaton
     * @return
     */
    @Override
    public PlayerBoardControllerInterface getPlayerBoard() {
        return (PlayerBoardController) this.board;
    }

    public void resetAfterRound() {
        this.passed = false;
    }


    public void setGameController(GameControllerController gameController) {
        this.gameController = gameController;
    }
}
