package server.sources.models.goods;

import client.source.components.harvest.AmethystComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class AmethystGood implements Good, Serializable {

    @Override
    public String isGood() {
        return "AMETHYST";
    }

    @Override
    public AnchorPane getGood() {
        return new AmethystComponent();
    }
}
