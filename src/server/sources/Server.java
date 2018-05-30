package server.sources;

import server.sources.exceptions.GameStartedException;
import server.sources.exceptions.ServerFullException;
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

    private final int SERVER_MIN_PLAYER = 2;
    private final int SERVER_MAX_PLAYER = 4;

    private final int SERVER_PORT = 1099;

    private enum ServerState { OFFLINE, LOBBY, RUNNING, ENDED }
    private ServerState state = ServerState.OFFLINE;

    private ArrayList<GameClientInterface> gameClients = new ArrayList<GameClientInterface>();
    private Game game = new Game((ServerInterface) this);

    public Server(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("Starting server");

        System.out.println("Setting security policy");
        System.setProperty("java.security.policy", getClass().getResource("server.policy").toString());
        System.out.println("Set security policy");

        LocateRegistry.createRegistry(this.SERVER_PORT);
        Naming.rebind("Server", this);

        System.out.println("Server started at \"/Server/\"");

        this.updateState(ServerState.LOBBY);

    }

    @Override
    public void registerClient(GameClientInterface gameClient) throws ServerFullException, GameStartedException, RemoteException {
        if (this.gameClients.size() >= this.SERVER_MAX_PLAYER) throw new ServerFullException();

        if (this.state != ServerState.LOBBY) throw new GameStartedException();

        Player player = new Player();

        // Link GameClient & Player
        player.setGame(this.getGame());
        player.setGameClient(gameClient);

        gameClient.setPlayer(player);

        // Set player
        getGame().players.add(player);
        this.gameClients.add(gameClient);

        this.promoteOwner();

        this.notifyClients(new UpdatePlayerListNotification());
    }

    @Override
    public void unregisterClient(GameClientInterface gameClient) throws RemoteException {

        this.getGame().removePlayer(gameClient);
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

    public void startGame() {
        this.updateState(ServerState.RUNNING);
        new Thread(getGame()).start();
    }

    private void updateState(ServerState state) {
        this.state = state;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void save() {

    }

    public static void main(String[] args) throws Exception {
        new Server(args);
    }
}
