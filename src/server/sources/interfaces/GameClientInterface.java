package server.sources.interfaces;

import client.source.Client;
import server.sources.Server;
import server.sources.models.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameClientInterface extends Remote {


    /**
     * Wrapper for clear request to the server
     * TODO: Decide to remove this
     * @param request
     * @throws RemoteException
     */
    public void requestRequest(RequestInterface request) throws RemoteException;

    /**
     * Wrapper for clear action request to the server
     * TODO: Decide to remove this
     * @param action
     * @throws RemoteException
     */
    public void requestAction(ActionInterface action) throws RemoteException;

    /**
     * Recieve notification from server
     * @param notification
     * @throws RemoteException
     */
    public void receiveNotification(NotificationInterface notification) throws RemoteException;

    public String getUsername() throws RemoteException;

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
    public Player getPlayer() throws RemoteException;

    /**
     * Set a new player object
     */
    public void setPlayer(Player player) throws RemoteException;
}
