package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 1-6-2018.
 */
public class BuildNotification implements NotificationInterface {


    private GameClientInterface target;

    public BuildNotification(GameClientInterface target){
        this.target = target;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showBuild();
    }
}