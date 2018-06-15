package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.stories.Reward;
import server.sources.models.stories.rewards.CiderReward;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LaborRewardNotification implements NotificationInterface {

    private ArrayList<VillagerInterface> villagers;
    private boolean ciderReward;

    public LaborRewardNotification(ArrayList<VillagerInterface> villagers, boolean ciderReward){
        this.villagers = villagers;
        if(ciderReward){
            this.ciderReward = ciderReward;
        }
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showLaborReward(this.villagers, this.ciderReward);
    }
}
