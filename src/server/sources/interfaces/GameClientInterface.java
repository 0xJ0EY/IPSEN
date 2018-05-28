package server.sources.interfaces;

import client.source.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface GameClientInterface extends Remote {

    /**
     * Send a clear request to the server
     * @param request request
     * @throws RemoteException
     */
    public void requestRequest(RequestInterface request) throws RemoteException;

    /**
     * Send an combination of a request and notification request to the server
     * @param action request
     * @throws RemoteException
     */
    public void requestAction(ActionInterface action) throws RemoteException;

    /**
     * Recieve notification from server
     * @param notification
     * @throws RemoteException
     */
    public void receiveNotification(NotificationInterface notification) throws RemoteException;

    /**
     * Return the set username
     * @return username
     * @throws RemoteException
     */
    public String getUsername() throws RemoteException;

    /**
     * Return the server interface if available
     * @return ServerInterface
     * @throws RemoteException
     */
    public ServerInterface getServer() throws RemoteException;

    /**
     * Return the available client
     * @return Client
     * @throws RemoteException
     */
    public Client getClient() throws RemoteException;

    /**
     * Promote the client to owner of the game
     * @throws RemoteException
     */
    public void promote() throws RemoteException;

    /**
     * Return the player object if there is one, else return null
     * @return Player
     * @throws RemoteException
     */
    public PlayerInterface getPlayer() throws RemoteException;

    /**
     * Set a new player object
     * @throws RemoteException
     */
    public void setPlayer(PlayerInterface player) throws RemoteException;

    /**
     * Return the randomly set UUID
     * @return
     * @throws RemoteException
     */
    public UUID getUUID() throws RemoteException;

    /**
     *
     * @param gameClient
     * @return
     * @throws RemoteException
     */
    public boolean equals(GameClientInterface gameClient) throws RemoteException;
}
