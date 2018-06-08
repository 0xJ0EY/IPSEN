package client.source.components.reward;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GoodRewardComponent extends RewardComponent {
    public GoodRewardComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/reward/good_reward.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
