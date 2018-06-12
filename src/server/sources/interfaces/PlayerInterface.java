package server.sources.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote, Serializable {


    public GameClientInterface getGameClient() throws RemoteException;

    public void setGameClient(GameClientInterface gameClient) throws RemoteException;

    public boolean hasPassed() throws RemoteException;

    public void pass() throws RemoteException;

    public void requestAction() throws RemoteException;

    public ActionInterface getAction() throws RemoteException;

    public boolean hasAction() throws RemoteException;

    public void doAction(ActionInterface action) throws RemoteException;

    public String getUsername() throws RemoteException;

    public PlayerBoardInterface getPlayerBoard() throws RemoteException;

    public int getReputation() throws RemoteException;

    public void changeReputation(int amount) throws RemoteException;

    /**
     * With this method, we can retrieve all amount of buildings that a player has build.
     * Each building represents one village points.
     * @return VP (Village Points)
     * @author Robin Silvério
     */
    public int getAmountBuildings() throws RemoteException;

}
