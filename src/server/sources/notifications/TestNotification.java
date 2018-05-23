package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class TestNotification  implements NotificationInterface {

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        System.out.println("Dit is een test notification");

    }

}
