package server.sources.notifications;

import client.source.components.reward.GoodRewardComponent;
import client.source.controllers.HarvestRewardController;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HarvestRewardNotification implements NotificationInterface {

    private ArrayList<GoodRewardComponent> goods;

    public HarvestRewardNotification(ArrayList<GoodRewardComponent> goods) {
        this.goods = goods;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showHarvestReward(this.goods);
    }
}
