package server.sources.notifications;

import client.source.components.reward.GoodRewardComponent;
import client.source.controllers.HarvestRewardController;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class HarvestRewardNotification implements NotificationInterface {

    private GoodRewardComponent good;

    public HarvestRewardNotification(GoodRewardComponent good) {
        this.good = good;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showHarvestReward(this.good);
    }
}
