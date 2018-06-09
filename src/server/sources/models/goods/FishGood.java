package server.sources.models.goods;

import client.source.components.harvest.FishComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class FishGood implements Good {

    @Override
    public AnchorPane getGood() {
        return new FishComponent();
    }
}
