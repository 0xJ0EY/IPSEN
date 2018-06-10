package server.sources.models.goods;

import client.source.components.harvest.FruitComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class FruitGood implements Good {

    @Override
    public String getBackground() {
        return "fruit.png";
    }

}
