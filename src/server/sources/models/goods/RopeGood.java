package server.sources.models.goods;

import client.source.components.harvest.RopeComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class RopeGood implements Good {

    @Override
    public AnchorPane getGood() {
        return new RopeComponent();
    }
}
