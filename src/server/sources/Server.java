package server.sources;

import server.sources.interfaces.*;
import server.sources.models.Player;
import server.sources.notifications.UpdatePlayerListNotification;

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
    public Game game = new Game((ServerInterface) this);

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
        player.setGame(this.game);
        player.setGameClient(gameClient);

        gameClient.setPlayer(player);

        // Set player
        game.players.add(player);
        this.gameClients.add(gameClient);

        this.promoteOwner();

        this.notifyClients(new UpdatePlayerListNotification());
    }

    @Override
    public void unregisterClient(GameClientInterface gameClient) throws RemoteException {

        this.game.removePlayer(gameClient);
        this.gameClients.remove(gameClient);

        this.promoteOwner();

        this.notifyClients(new UpdatePlayerListNotification());
    }

    /**
     * Always promote the first client in the list
     * @throws RemoteException
     */
    private void promoteOwner() throws RemoteException {

        if (this.gameClients.size() > 0) {
            this.gameClients.get(0).promote();
        }

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

    public void startGame() {
        new Thread(game).start();
    }

    public void save() {
        // TODO: THIS
    }

    public void load() {
        // TODO: THIS
    }
}
