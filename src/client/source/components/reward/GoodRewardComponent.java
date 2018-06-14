package client.source.components.reward;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.Good;
import server.sources.models.stories.rewards.GoodReward;

import java.io.IOException;
import java.io.Serializable;

/**
 * A class that creates good reward
 * Created by Richard Kerkvliet
 */
public class GoodRewardComponent extends RewardComponent<GoodReward> implements Serializable{

    protected GoodReward good;

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

    public void load()  {
        this.background.setStyle(
            "-fx-background-image: url('/client/resources/img/goods/" + this.good.isGood().getBackground() + "');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 50 50"
        );
    }

    @Override
    public GoodReward getModel() {
        return good;
    }

    @Override
    public void setModel(GoodReward obj) {
        this.good = obj;
    }
}
