package server.sources.models.goods;

import client.source.components.harvest.MushroomComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class MushroomGood implements Good, Serializable {

    @Override
    public String isGood() {
        return "MUSHROOM";
    }

    @Override
    public AnchorPane getGood() {
        return new MushroomComponent();
    }
}
