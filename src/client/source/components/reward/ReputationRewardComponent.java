package client.source.components.reward;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ReputationRewardComponent extends RewardComponent{
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
}
