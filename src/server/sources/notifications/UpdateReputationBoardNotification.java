package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.ReputationBoardInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class UpdateReputationBoardNotification implements NotificationInterface {

    private ReputationBoardInterface reputationBoard;

    public UpdateReputationBoardNotification(ReputationBoardInterface reputationBoard) {
        this.reputationBoard = reputationBoard;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().reputationBoardObserver.setState(this.reputationBoard);
    }

}
