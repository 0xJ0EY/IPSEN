package server.sources.models.goods;

import client.source.components.harvest.FruitComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class FruitGood  implements Good, Serializable {

    @Override
    public String isGood() {
        return "FRUIT";
    }

    @Override
    public AnchorPane getGood() {
        return new FruitComponent();
    }
}
