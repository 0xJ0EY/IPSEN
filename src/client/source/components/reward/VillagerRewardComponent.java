package client.source.components.reward;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.stories.rewards.VillagerReward;
import server.sources.models.villagers.Villager;

import javax.swing.event.AncestorEvent;
import java.io.IOException;
import java.rmi.RemoteException;

public class VillagerRewardComponent extends RewardComponent<VillagerReward> {
    private VillagerReward villager;

    public VillagerRewardComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/reward/villager_reward.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() throws RemoteException {
        this.background.setStyle(
            "-fx-background-image: url('/client/resources/img/villager_backgrounds/special_villagers/" + this.villager.getVillagerBackground() + "');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 50 50"
        );
    }

    @Override
    public VillagerReward getModel() {
        return villager;
    }

    @Override
    public void setModel(VillagerReward obj) {
        this.villager = obj;
    }
}
