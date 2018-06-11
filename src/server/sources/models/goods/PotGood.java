package server.sources.models.goods;

import client.source.components.harvest.ClayPotComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class PotGood implements Good {

    @Override
    public String getBackground() {
        return "pot.png";
    }

    @Override
    public boolean sameInstance(Good good) {
        return good instanceof PotGood;
    }
}
