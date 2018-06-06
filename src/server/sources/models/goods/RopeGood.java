package server.sources.models.goods;

import client.source.components.harvest.RopeComponent;
import javafx.scene.layout.AnchorPane;

public class RopeGood implements Good {
    @Override
    public String isGood() {
        return "ROPE";
    }

    @Override
    public AnchorPane getGood() {
        return new RopeComponent();
    }
}
