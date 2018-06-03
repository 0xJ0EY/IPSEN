package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.PlayerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class UpdatePlayerListNotification implements NotificationInterface {

    private ArrayList<PlayerInterface> players;

    // Set the current list of game clients
    public UpdatePlayerListNotification(ArrayList<PlayerInterface> players) {
        this.players = players;
    }

    /**
     * Update the lobby list via the client observer
     * @author Joey de Ruiter
     * @param gameClient
     */
    @Override
    public void execute(GameClientInterface gameClient) {

        try {
            gameClient.getClient().clientObserver.setState(this.players);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
