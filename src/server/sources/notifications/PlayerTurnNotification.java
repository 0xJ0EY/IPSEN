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

        gameClient.getClient().turnObserver.setState(target.getPlayer());

        if (this.target.equals(gameClient)) {
            gameClient.getClient().messageObserver.setState("It's your turn");

        } else {

            gameClient.getClient().messageObserver.setState(
                String.format("It\'s %s\'s turn", this.target.getPlayer().getUsername())
            );
        }

    }
}
