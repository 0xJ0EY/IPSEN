package server.sources;

import server.sources.interfaces.*;
import server.sources.models.Player;
import server.sources.notifications.PlayerConnectedNotification;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private final int SERVER_MAX_PLAYER = 4;
    private final int SERVER_PORT = 1099;

    private ArrayList<GameClientInterface> gameClients = new ArrayList<GameClientInterface>();
    public Game game = new Game(this);

    public Server() throws RemoteException, MalformedURLException {
        System.out.println("Starting server");

        System.out.println("Setting security policy");
        System.setProperty("java.security.policy", getClass().getResource("server.policy").toString());
        System.out.println("Set security policy");

        LocateRegistry.createRegistry(this.SERVER_PORT);
        Naming.rebind("Server", this);

        System.out.println("Server started at \"/Server/\"");

    }

    @Override
    public void registerClient(GameClientInterface gameClient) throws RemoteException {
        Player player = new Player();

        // Link GameClient & Player
        player.setGameClient(gameClient);
        gameClient.setPlayer(player);

        // Set
        this.gameClients.add(gameClient);
        game.players.add(player);

        if (this.gameClients.size() == 1) {
            gameClient.promote();
        }

        this.notifyClients(new PlayerConnectedNotification());
    }

    @Override
    public void unregisterClient(GameClientInterface gameClient) {
        // TODO Remove player object from the game
        this.gameClients.remove(gameClient);
    }

    @Override
    public void notifyClients(NotificationInterface notification) throws RemoteException {
        for (GameClientInterface gameClient : this.gameClients) {
            gameClient.receiveNotification(notification);
        }
    }

    @Override
    public void executeRequest(RequestInterface request) throws RemoteException {
        // Run code server side
        request.execute(this);
    }

    @Override
    public void executeAction(ActionInterface action) throws RemoteException {
        // Run code server side
        action.execute(this);

        // Update all the clients
        this.notifyClients(action.update());

    }

    @Override
    public ArrayList<GameClientInterface> listCurrentClients() {
        return this.gameClients;
    }


    public static void main(String[] args) throws Exception {
        new Server();
    }

    public void save() {
        // TODO: THIS
    }

    public void load() {
        // TODO: THIS
    }
}
