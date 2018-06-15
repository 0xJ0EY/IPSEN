package server.sources.interfaces;

import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface so every player can be a remote object on the server
 * @author Joey de Ruiter
 */
public interface PlayerInterface extends Remote, Serializable {

    public GameClientInterface getGameClient() throws RemoteException;

    public void setGameClient(GameClientInterface gameClient) throws RemoteException;

    public boolean hasPassed() throws RemoteException;

    /**
     * Set the pass flag for this player,
     * that means the player wont get a turn this round anymore.
     * @throws RemoteException java.rmi.RemoteException
     */
    public void pass() throws RemoteException;

    /**
     * Request a doAction for the player turn.
     *
     * @author Joey de Ruiter
     * @throws RemoteException java.rmi.RemoteException
     */
    public void requestAction() throws RemoteException;

    /**
     * Return the action set by doAction()
     *
     * @author Joey de Ruiter
     * @return ActionInterface
     * @throws RemoteException java.rmi.RemoteException
     */
    public ActionInterface getAction() throws RemoteException;

    /**
     * Return true if an action has been set
     *
     * @author Joey de Ruiter
     * @return boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    public boolean hasAction() throws RemoteException;

    /**
     * Set an action to be executed do this turn
     *
     * @author Joey de Ruiter
     * @param action
     * @throws RemoteException java.rmi.RemoteException
     */
    public void doAction(ActionInterface action) throws RemoteException;

    public String getUsername() throws RemoteException;

    public PlayerBoardInterface getPlayerBoard() throws RemoteException;

    public int getReputation() throws RemoteException;

    /**
     * With this method, we can retrieve all amount of buildings that a player has build.
     * Each building represents one village points.
     *
     * @return VP (Village Points)
     * @author Robin Silverio
     */
    public int getAmountBuildings() throws RemoteException;

    /**
     * Set reputation based on an int
     *
     * @author Richard Kerkvliet
     * @param amount
     * @throws RemoteException java.rmi.RemoteException
     */
    public void changeReputation(int amount) throws RemoteException;
}
