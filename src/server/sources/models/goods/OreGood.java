package server.sources.models.goods;

import client.source.components.harvest.OreComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class OreGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "ORE";
    }

    @Override
    public AnchorPane getGood() {
        return new OreComponent();
    }
}
