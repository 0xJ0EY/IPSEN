package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 7-6-2018.
 */
public class TrainNotification implements NotificationInterface {

    private GameClientInterface target;

    public TrainNotification(GameClientInterface target){
        this.target = target;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showTrain();
    }
}
