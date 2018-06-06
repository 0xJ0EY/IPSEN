package server.sources.models.goods;

import client.source.components.harvest.FruitComponent;
import javafx.scene.layout.AnchorPane;

public class FruitGood  implements Good {
    @Override
    public String isGood() {
        return "FRUIT";
    }

    @Override
    public AnchorPane getGood() {
        return new FruitComponent();
    }
}
