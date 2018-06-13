package client.source.components.reward;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.stories.rewards.CoinReward;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class that creates coin reward
 * Created by Richard Kerkvliet
 */
public class CoinRewardComponent extends RewardComponent<CoinReward> {

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

    @Override
    public void load() throws RemoteException {
        this.background.setStyle(
            "-fx-background-image: url('/client/resources/img/rewards/coin.png" + "');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 50 50"
        );
    }

    @Override
    public CoinReward getModel() {
        return null;
    }

    @Override
    public void setModel(CoinReward obj) {

    }

}
