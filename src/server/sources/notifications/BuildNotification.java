package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 1-6-2018.
 */
public class BuildNotification implements NotificationInterface {


    private GameClientInterface target;
    private ArrayList<VillagerInterface> villagers;

    public BuildNotification(GameClientInterface target, ArrayList<VillagerInterface> villagers){
        this.target = target;
        this.villagers = villagers;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showBuild(this.villagers);
    }
}