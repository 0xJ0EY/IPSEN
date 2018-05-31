package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class GameStartedNotification implements NotificationInterface {
    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        // Show main game client for everyone
        gameClient.getClient().showMain();
    }
}
