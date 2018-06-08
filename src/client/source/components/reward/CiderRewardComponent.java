package client.source.components.reward;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CiderRewardComponent extends RewardComponent {
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
}
