package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class ExploreRunNotification implements NotificationInterface {
    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        // Show main gameController client for everyone

        System.out.println("Hallo");
        gameClient.getClient().showMain();
    }
}
