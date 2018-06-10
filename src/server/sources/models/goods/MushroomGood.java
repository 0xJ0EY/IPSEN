package server.sources.models.goods;

import client.source.components.harvest.MushroomComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class MushroomGood implements Good {

    @Override
    public String getBackground() {
        return "mushroom.png";
    }

}
