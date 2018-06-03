package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class UpdatePlayerListNotification implements NotificationInterface {


    private ArrayList<GameClientInterface> gameClients;

    // Set the current list of game clients
    public UpdatePlayerListNotification(ArrayList<GameClientInterface> gameClients) {
        this.gameClients = gameClients;
    }

    /**
     * Update the lobby list via the client observer
     * @author Joey de Ruiter
     * @param gameClient
     */
    @Override
    public void execute(GameClientInterface gameClient) {

        try {
            gameClient.getClient().clientObserver.setState(this.gameClients);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
