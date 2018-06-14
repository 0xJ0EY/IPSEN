package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LaborRewardNotification implements NotificationInterface {

    private ArrayList<VillagerInterface> villagers;

    public LaborRewardNotification(ArrayList<VillagerInterface> villagers){
        this.villagers = villagers;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showLaborReward(this.villagers);
    }
}
