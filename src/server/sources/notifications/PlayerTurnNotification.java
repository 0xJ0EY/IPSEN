package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class PlayerTurnNotification implements NotificationInterface {

    private GameClientInterface target;

    public PlayerTurnNotification(GameClientInterface target) {
        this.target = target;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        if (this.target.equals(gameClient)) {
            gameClient.getClient().main.menuController.enableTurnButton();

        } else {
            gameClient.getClient().main.menuController.disableTurnButton();

        }

    }
}
