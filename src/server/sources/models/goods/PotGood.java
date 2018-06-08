package server.sources.models.goods;

import client.source.components.harvest.ClayPotComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class PotGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "POT";
    }

    @Override
    public AnchorPane getGood() {
        return new ClayPotComponent();
    }
}
