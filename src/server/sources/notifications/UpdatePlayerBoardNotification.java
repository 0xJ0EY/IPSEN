package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.PlayerBoardInterface;

import java.rmi.RemoteException;

public class UpdatePlayerBoardNotification implements NotificationInterface {

    private PlayerBoardInterface playerboard;

    public UpdatePlayerBoardNotification(PlayerBoardInterface playerboard) {
        this.playerboard = playerboard;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().playerBoardObserver.setState(this.playerboard);
    }
}
