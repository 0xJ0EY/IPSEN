package server.sources.models.goods;

import client.source.components.reward.GoodComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

/**
 * @author Jan Douwe
 */
public class CiderGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "FISH";
    }

    @Override
    public AnchorPane getGood() {
        return new GoodComponent();
    }
}
