package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class BuyDeclinedNotification implements NotificationInterface {

    private GameClientInterface target;

    public BuyDeclinedNotification(GameClientInterface target){
        this.target = target;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().turnObserver.setState(target.getPlayer());

        gameClient.getClient().messageObserver.setState("The trade has not been accepted.");

    }
}
