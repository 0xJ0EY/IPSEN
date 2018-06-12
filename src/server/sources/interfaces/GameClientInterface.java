package server.sources.interfaces;

import client.source.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

/**
 * Interface used as Remote stub for Java RMI,
 * this stub will be used in every gameClient connected to the server.
 * @author Joey de Ruiter
 */
public interface GameClientInterface extends Remote {

    /**
     * Send a clear request to the server.
     *
     * @param request request
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void requestRequest(RequestInterface request) throws RemoteException;

    /**
     * Send an combination of a request and notification request to the server.
     *
     * @param action request
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void requestAction(ActionInterface action) throws RemoteException;

    /**
     * Receive notification from server.
     *
     * @param notification
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void receiveNotification(NotificationInterface notification) throws RemoteException;

    /**
     * Return the server interface if available.
     *
     * @return ServerInterface
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public ServerInterface getServer() throws RemoteException;

    /**
     * Return the available client.
     *
     * @return Client
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public Client getClient() throws RemoteException;

    /**
     * Promote the client to owner of the gameController.
     *
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void promote() throws RemoteException;

    /**
     * Get if the client is the owner or not.
     *
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public boolean isOwner() throws RemoteException;

    /**
     * Return the player object if there is one, else return null.
     *
     * @return Player
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public PlayerInterface getPlayer() throws RemoteException;

    /**
     * Set a new player object.
     *
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void setPlayer(PlayerInterface player) throws RemoteException;

    /**
     * Return the randomly set UUID.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException
     */
    public UUID getUUID() throws RemoteException;

    /**
     * Check if game clients are equal based on the UUID.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @return
     * @throws RemoteException
     */
    public boolean equals(GameClientInterface gameClient) throws RemoteException;
}
