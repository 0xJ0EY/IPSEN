package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.stories.Option;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RewardScreenNotification implements NotificationInterface {

    private Option option;
    private ArrayList<VillagerInterface> villagers;

    public RewardScreenNotification(Option option, ArrayList<VillagerInterface> villagers){
        this.option = option;
        this.villagers = villagers;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showRewards(option, villagers);
    }
}
