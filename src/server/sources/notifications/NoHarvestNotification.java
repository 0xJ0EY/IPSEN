package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class NoHarvestNotification implements NotificationInterface {

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().messageObserver.setState("Deze building heeft geen goods meer over.");
    }
}
