package client.source.components.reward;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CoinRewardComponent extends RewardComponent {

    public CoinRewardComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/reward/coin_reward.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
