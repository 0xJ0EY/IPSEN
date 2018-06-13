package server.sources.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    /**
     * Register an user at the game, also create a Player object in the GameController.
     *
     * @author Joey de Ruiter
     * @param gameClientInterface the assigned gameClient
     * @param username
     * @throws RemoteException java.rmi.RemoteException
     */
    public void registerClient(GameClientInterface gameClientInterface, String username) throws RemoteException;

    /**
     * Remove an user from the game, also remove the player from the GameController.
     *
     * @author Joey de Ruiter
     * @param gameClientInterface
     * @throws RemoteException java.rmi.RemoteException
     */
    public void unregisterClient(GameClientInterface gameClientInterface) throws RemoteException;

    /**
     * Send an notification object to every connected client.
     *
     * @author Joey de Ruiter
     * @param notification the NotificationInterface to be executed on the client
     * @throws RemoteException java.rmi.RemoteException
     */
    public void notifyClients(NotificationInterface notification) throws RemoteException;

    /**
     * Execute a request at the server.
     *
     * @author Joey de Ruiter
     * @param request
     * @throws RemoteException java.rmi.RemoteException
     */
    public void executeRequest(RequestInterface request) throws RemoteException;

    /**
     * Execute an action object on the server and send the action notifcation to every client
     * @param action
     * @throws RemoteException java.rmi.RemoteException
     */
    public void executeAction(ActionInterface action) throws RemoteException;

    /**
     * Return the game controller, so the client can retrieve data via RMI.
     *
     * @author Joey de Ruiter
     * @return GameControllerInterface
     * @throws RemoteException java.rmi.RemoteException
     */
    public GameControllerInterface getGameController() throws RemoteException;

}
