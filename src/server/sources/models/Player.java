package server.sources.models;

import server.sources.controllers.GameController;
import server.sources.enumerations.PlayerColour;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.notifications.PlayerTurnNotification;
import server.sources.notifications.UpdatePlayerListNotification;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Player extends UnicastRemoteObject implements PlayerInterface {

    private static final long serialVersionUID = 1337L;

    private PlayerBoard board = new PlayerBoard(this);

    private GameController gameController;

    private transient GameClientInterface gameClient;

    private PlayerColour colour = null;

    private ActionInterface action = null;

    private boolean passed = false;

    private String username;

    public Player(String username, int id) throws RemoteException {
        this.username = username;
        this.colour = PlayerColour.getColourByIndex(id);
    }

    public GameClientInterface getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    public boolean hasPassed() {
        return this.passed;
    }

    /**
     * Set the pass flag for this player,
     * that means the player wont get a turn this round anymore.
     * @throws RemoteException java.rmi.RemoteException
     */
    public void pass() throws RemoteException {
        this.passed = true;
    }

    /**
     * Request a doAction for the player turn.
     *
     * @author Joey de Ruiter
     * @throws RemoteException java.rmi.RemoteException
     */
    public void requestAction() throws RemoteException {
        this.action = null;
        gameController.getServer().notifyClients(new PlayerTurnNotification(this.gameClient));
    }

    /**
     * Return the action set by doAction()
     *
     * @author Joey de Ruiter
     * @return ActionInterface
     * @throws RemoteException java.rmi.RemoteException
     */
    public ActionInterface getAction() throws RemoteException {
        return this.action;
    }

    /**
     * Return true if an action has been set
     *
     * @author Joey de Ruiter
     * @return boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    public boolean hasAction() throws RemoteException {
        return this.action != null;
    }

    /**
     * Set an action to be executed do this turn
     *
     * @author Joey de Ruiter
     * @param action
     * @throws RemoteException java.rmi.RemoteException
     */
    public void doAction(ActionInterface action) throws RemoteException {
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

    public void resetAfterRound() {
        this.passed = false;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public PlayerColour getColour() {
        return colour;
    }

}
