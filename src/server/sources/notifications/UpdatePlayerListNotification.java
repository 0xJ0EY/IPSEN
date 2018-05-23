package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class UpdatePlayerListNotification implements NotificationInterface {
    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        // Force that every client updates the lobby list
        gameClient.getClient().lobby.updateLobbyList();
    }
}
