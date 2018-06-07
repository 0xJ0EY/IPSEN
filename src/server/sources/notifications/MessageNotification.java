package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class MessageNotification implements NotificationInterface {

    private String message;

    public MessageNotification(String message) {
        this.message = message;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().messageObserver.setState(message);
    }
}
