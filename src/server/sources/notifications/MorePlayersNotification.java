package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class MorePlayersNotification implements NotificationInterface {

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        System.out.println("kaas");
        gameClient.getClient().messageObserver.setState(
                "You need more players in order to play the game."
        );
    }
}
