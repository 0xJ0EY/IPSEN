package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.GameControllerInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class GameControllerUpdateNotifcation implements NotificationInterface {

    GameControllerInterface gameController;

    public GameControllerUpdateNotifcation(GameControllerInterface gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().gameObserver.setState(gameController);
    }
}
