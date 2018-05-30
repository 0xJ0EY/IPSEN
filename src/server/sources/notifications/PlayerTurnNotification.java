package server.sources.notifications;

import client.source.Client;
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
            Client client = gameClient.getClient();
            client.getMain().showMessage("It\'s your turn");
            client.getMain().menuController.enableTurnButton();

        } else {
            Client client = gameClient.getClient();
            client.getMain().showMessage(String.format("It\'s %s\'s turn", this.target.getUsername()));
            client.getMain().menuController.disableTurnButton();

        }

    }
}
