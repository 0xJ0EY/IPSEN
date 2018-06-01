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

    public PlayerBoardControllerInterface getPlayerBoard() throws RemoteException;

}
