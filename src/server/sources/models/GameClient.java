package server.sources.models;

import client.source.Client;
import server.sources.interfaces.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class GameClient extends UnicastRemoteObject implements GameClientInterface {

    private ServerInterface server;
    private Client client;
    private PlayerInterface player;

    private boolean connected = false;

    private boolean owner = false;

    private UUID uuid = UUID.randomUUID();

    public GameClient(Client client) throws RemoteException {
        super();
        this.client = client;
    }

    /**
     * Connect to the game server.
     *
     * @param address
     * @param username
     * @throws NotBoundException
     * @throws MalformedURLException
     * @throws RemoteException
     */
    public void connect(String address, String username) throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("Setting security policy");
        System.setProperty("java.security.policy", getClass().getResource("/server/sources/policies/client.policy").toString());
        System.out.println("Set security policy");

        this.server = (ServerInterface) Naming.lookup("//" + address + "/Server");
        this.server.registerClient(this, username);
        this.connected = true;

    }

    /**
     * Disconnect from the game server.
     *
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void disconnect() throws RemoteException {

        // Only disconnect when connected to a server
        if (this.connected) {
            // Clear gameClient of client
            this.client.setGameClient(new GameClient(this.client));

            // Send unregister request to the server
            this.server.unregisterClient((GameClientInterface) this);

            this.connected = false;
        }

    }

    /**
     * Send a clear request to the server.
     *
     * @param request request
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public void requestRequest(RequestInterface request) throws RemoteException {
        this.server.executeRequest(request);
    }

    /**
     * Send an combination of a request and notification request to the server.
     *
     * @param action request
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public void requestAction(ActionInterface action) throws RemoteException {
        this.server.executeAction(action);
    }

    /**
     * Recieve notification from server.
     *
     * @param notification
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public void receiveNotification(NotificationInterface notification) throws RemoteException {
         notification.execute(this);
    }

    /**
     * Return the server interface if available.
     *
     * @return ServerInterface
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public ServerInterface getServer() throws RemoteException {
        return this.server;
    }

    /**
     * Return the available client.
     *
     * @return Client
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public Client getClient() throws RemoteException {
        return this.client;
    }

    /**
     * Promote the client to owner of the gameController.
     *
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public void promote() throws RemoteException {
        this.owner = true;
    }

    /**
     * Get if the client is the owner or not.
     *
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public boolean isOwner() throws RemoteException {
        return this.owner;
    }

    /**
     * Return the player object if there is one, else return null.
     *
     * @return Player
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public PlayerInterface getPlayer() throws RemoteException {
        return this.player;
    }

    /**
     * Set a new player object.
     *
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public void setPlayer(PlayerInterface player) throws RemoteException {
        this.player = player;
    }

    /**
     * Return the randomly set UUID.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException
     */
    @Override
    public UUID getUUID() throws RemoteException {
        return this.uuid;
    }

    /**
     * Check if game clients are equal based on the UUID.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean equals(GameClientInterface gameClient) throws RemoteException {
        return this.uuid.equals(gameClient.getUUID());
    }
}
