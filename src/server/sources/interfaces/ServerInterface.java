package server.sources.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    public void registerClient(GameClientInterface gameClientInterface, String username) throws RemoteException;

    public void unregisterClient(GameClientInterface gameClientInterface) throws RemoteException;

    public void notifyClients(NotificationInterface notification) throws RemoteException;

    public void executeRequest(RequestInterface request) throws RemoteException;

    public void executeAction(ActionInterface action) throws RemoteException;

    public GameControllerInterface getGameController() throws RemoteException;

}
