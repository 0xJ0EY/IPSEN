package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class EndOfGameNotification implements NotificationInterface {

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        gameClient.getClient().showMessage("GameController has ended");

    }

}
