package server.sources;

import server.sources.models.Player;
import server.sources.notifications.GameStartedNotification;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Game implements Serializable {

    private enum GameStates { LOBBY, STARTED, RUNNING, ENDED }

    public ArrayList<Player> players = new ArrayList<Player>();

    private Server server;
    private GameStates gameState = GameStates.LOBBY;


    public Game(Server server) {
        this.server = server;
    }

    public void play()throws RemoteException {

        this.start();

        this.run();

        this.end();

    }

    private void start() throws RemoteException {
        this.setGameState(GameStates.STARTED);

        // Send everyone to the main screen
        this.server.notifyClients(new GameStartedNotification());

        System.out.println("Game started");

    }

    private void run() {
        this.setGameState(GameStates.RUNNING);

        System.out.println("Game running");

    }

    private void end() {
        this.setGameState(GameStates.ENDED);

        System.out.println("Game ended");

    }

    private void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

}
