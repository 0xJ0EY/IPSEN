package client.source.components.reward;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.stories.rewards.ReputationReward;

import java.io.IOException;
import java.rmi.RemoteException;

public class ReputationRewardComponent extends RewardComponent<ReputationReward> {

    public ReputationRewardComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/reward/reputation_reward.fxml"));

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
            "-fx-background-image: url('/client/resources/img/rewards/reputation.png" + "');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 50 50"
        );
    }

    @Override
    public ReputationReward getModel() {
        return null;
    }

    @Override
    public void setModel(ReputationReward obj) {

    }
}
