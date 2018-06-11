package server.sources.models.goods;

import client.source.components.harvest.FishComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class FishGood implements Good {

    @Override
    public String getBackground() {
        return "fish.png";
    }

    @Override
    public boolean sameInstance(Good good) {
        return good instanceof FishGood;
    }
}
