package server.sources;

import server.sources.controllers.GameController;
import server.sources.exceptions.GameStartedException;
import server.sources.exceptions.ServerFullException;
import server.sources.interfaces.*;
import server.sources.models.Player;
import server.sources.models.ReputationBoard;
import server.sources.notifications.LobbyNotification;
import server.sources.notifications.SaveGameNotification;
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

    private enum ServerState { OFFLINE, LOBBY, LOADED, RUNNING, ENDED }
    private ServerState state = ServerState.OFFLINE;

    private ArrayList<GameClientInterface> gameClients = new ArrayList<GameClientInterface>();
    private GameController gameController = new GameController((ServerInterface) this);
    private ReputationBoard reputationBoard;

    public Server(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("Starting server");

        System.out.println("Setting security policy");
        System.setProperty("java.security.policy", getClass().getResource("policies/server.policy").toString());
        System.out.println("Set security policy");

        LocateRegistry.createRegistry(this.SERVER_PORT);
        Naming.rebind("Server", this);

        System.out.println("Server started at \"/Server/\"");

        this.updateState(ServerState.LOBBY);

    }

    /**
     * Register a client
     * This will also create a player object in the GameController.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param username
     * @throws ServerFullException when server is full
     * @throws GameStartedException when the game has already started
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public void registerClient(GameClientInterface gameClient, String username) throws ServerFullException, GameStartedException, RemoteException {
        if (this.gameClients.size() >= this.SERVER_MAX_PLAYER) throw new ServerFullException();

        if (this.state != ServerState.LOBBY) throw new GameStartedException();

        Player player = new Player(username);

        // Link GameClient & Player
        player.setGameController(this.gameController);
        player.setGameClient(gameClient);

        gameClient.setPlayer(player);

        // Set player
        this.gameController.players.add(player);
        this.gameClients.add(gameClient);

        this.promoteOwner();

        ArrayList<PlayerInterface> players = (ArrayList<PlayerInterface>) (ArrayList<?>) this.gameController.players;
        reputationBoard = new ReputationBoard(players);

        // Update target client
        gameClient.receiveNotification(new LobbyNotification());

        // Update all clients
        this.notifyClients(new UpdatePlayerListNotification(players));
    }

    /**
     * Unregister the player.
     * This will also remove the player object form the GameController.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public void unregisterClient(GameClientInterface gameClient) throws RemoteException {

        this.gameController.removePlayer(gameClient);
        this.gameClients.remove(gameClient);

        // Maybe promote a new owner
        this.promoteOwner();

        ArrayList<PlayerInterface> players = (ArrayList<PlayerInterface>) (ArrayList<?>) this.gameController.players;

        this.notifyClients(new UpdatePlayerListNotification(players));
    }

    /**
     * Always promote the first client in the list
     * @throws RemoteException java.rmi.RemoteException
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

    /**
     * Return the current Game as a interface so that the client can retrieve data via Java RMI
     * @return GameControllerInterface
     */
    @Override
    public GameControllerInterface getGameController() {
        return gameController;
    }

    public void startGame() {
        this.updateState(ServerState.RUNNING);
        new Thread(this.gameController).start();
    }

    private void updateState(ServerState state) {
        this.state = state;
    }

    /**
     * Send the GameController
     *
     * @author Joey de Ruiter
     * @param target
     */
    public void save(GameClientInterface target) {

        try {
            target.receiveNotification(new SaveGameNotification(this.gameController));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Load the gameController from a other gameController that was created from a byte array.
     *
     * @author Joey de Ruiter
     * @param gameController
     * @throws RemoteException java.rmi.RemoteException
     */
    public void load(GameController gameController) throws RemoteException {

        if (gameController.players.size() != this.gameController.players.size()) {
            System.out.println("[System] Amount of players dont match");

            return;
        }

        this.updateState(ServerState.LOADED);

        for (PlayerInterface player : gameController.players ) {
            for (GameClientInterface gameClient : this.gameClients) {
                gameClient.setPlayer(player);
                player.setGameClient(gameClient);
            }
        }

        this.startGame();
    }

    public static void main(String[] args) throws Exception {
        new Server(args);
    }
}
