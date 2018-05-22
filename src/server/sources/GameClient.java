package server.sources;

import client.source.Client;
import server.sources.interfaces.*;
import server.sources.models.Player;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameClient extends UnicastRemoteObject implements GameClientInterface {

    private ServerInterface server;
    private Client client;
    private String username;
    private Player player;

    private boolean owner = false;

    public GameClient(Client client) throws RemoteException {
        super();
        this.client = client;
    }

    public void connect(String address, String username) throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("Setting security policy");
        System.setProperty("java.security.policy", getClass().getResource("client.policy").toString());
        System.out.println("Set security policy");

        this.username = username;

        this.server = (ServerInterface) Naming.lookup("//" + address + "/Server");
        this.server.registerClient(this);
    }

    @Override
    public void requestRequest(RequestInterface request) throws RemoteException {
        this.server.executeRequest(request);
    }

    @Override
    public void requestAction(ActionInterface action) throws RemoteException {
        this.server.executeAction(action);
    }

    @Override
    public void receiveNotification(NotificationInterface notification) throws RemoteException {
         notification.execute(this);
    }

    public String getUsername() throws RemoteException {
        return this.username;
    }

    @Override
    public ServerInterface getServer() throws RemoteException {
        return this.server;
    }

    @Override
    public Client getClient() throws RemoteException {
        return this.client;
    }

    @Override
    public void promote() throws RemoteException {
        this.owner = true;
        this.client.lobby.enableStartButton();
    }

    @Override
    public Player getPlayer() throws RemoteException {
        return this.player;
    }

    @Override
    public void setPlayer(Player player) throws RemoteException {
        this.player = player;
    }
}
