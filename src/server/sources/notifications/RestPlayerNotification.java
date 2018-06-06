package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class RestPlayerNotification implements NotificationInterface {

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        // Clear everyone's access to the turn page
        gameClient.getClient().turnObserver.setState(null);

        // Show villager main screen
        gameClient.getClient().showVillagerRest();
    }

}
