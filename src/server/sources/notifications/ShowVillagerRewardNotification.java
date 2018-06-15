package server.sources.notifications;

import client.source.components.villager.MarketVillagerComponent;
import client.source.components.villager.VillagerComponent;
import client.source.components.villager_to_train.TrainerVillagerComponent;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerInterface;

import java.rmi.RemoteException;

public class ShowVillagerRewardNotification implements NotificationInterface {

    private VillagerInterface villager;
    private int price;

    public ShowVillagerRewardNotification(VillagerInterface villager, int price) {
        this.villager = villager;
        this.price = price;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        MarketVillagerComponent villagerComponent = new MarketVillagerComponent();
        villagerComponent.setModel(this.villager);
        villagerComponent.setPrice(this.price);
        villagerComponent.load();

        gameClient.getClient().showTrainReward(villagerComponent);
    }

}
