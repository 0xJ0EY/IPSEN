package server.sources.models.goods;

import client.source.components.harvest.FishComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class FishGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "FISH";
    }

    @Override
    public AnchorPane getGood() {
        return new FishComponent();
    }
}
