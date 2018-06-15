package client.source.components.reward;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.stories.rewards.CiderReward;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class that creates cider reward
 * Created by Richard Kerkvliet
 */
public class CiderRewardComponent extends RewardComponent<CiderReward> {

    public CiderRewardComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/reward/cider_reward.fxml"));

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
            "-fx-background-image: url('/client/resources/img/perks/cider.png" + "');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 50 50"
        );
    }

    @Override
    public void setModel(CiderReward obj) {

    }

    @Override
    public CiderReward getModel() {
        return null;
    }
}
