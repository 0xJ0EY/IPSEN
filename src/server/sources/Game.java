package server.sources;

import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.ServerInterface;
import server.sources.models.Player;
import server.sources.notifications.GameStartedNotification;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Game implements Runnable, Serializable {

    private enum GameStates { LOBBY, STARTED, RUNNING, ENDED }

    public ArrayList<Player> players = new ArrayList<Player>();

    public ServerInterface server;
    private GameStates gameState = GameStates.LOBBY;

    private final int MAX_ROUNDS = 7;

    private int round = 0;
    private int turn = 0;

    public Game(ServerInterface server) {
        this.server = server;
    }

    public void play()throws RemoteException {

        this.startGame();

        this.runGame();

        this.endGame();

    }

    private void startGame() throws RemoteException {
        this.setGameState(GameStates.STARTED);

        // Send everyone to the main screen
        System.out.println("Send notification");
        this.server.notifyClients(new GameStartedNotification());


        Player player = this.players.get(0);
        player.requestAction();

        System.out.println("Game started");

    }

    public void runGame() {
        this.setGameState(GameStates.RUNNING);

        while (!this.gameHasEnded()) {

            do {
                Player player = players.get((this.round + this.turn) % players.size());

                if (!player.hasPassed()) {

                    try {
                        player.requestAction();

                        while (!player.hasAction()) {
                            Thread.sleep(100);

                            System.out.println(player);

                            System.out.println("Polling for response");
                        }

                        System.out.println("Execute action");
                        this.server.executeAction(player.getAction());

                        player.pass();

                    } catch (RemoteException | InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                this.turn++;
            } while(!this.roundHasEnded());

            for (Player player : this.players) {
                player.resetAfterRound();
            }

            this.turn = 0;
            this.round++;
        }

    }

    private void endGame() {
        this.setGameState(GameStates.ENDED);

        System.out.println("Game ended");

    }

    @Override
    public void run() {

        try {
            this.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private boolean roundHasEnded() {
        boolean hasEnded = true;

        for (Player player : this.players) {
            if (player.hasPassed()) hasEnded = false;
        }

        return hasEnded;
    }

    private boolean gameHasEnded() {
        return this.round >= this.MAX_ROUNDS;
    }

    private void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

}
