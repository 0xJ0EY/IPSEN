package server.sources.models.goods;

import client.source.components.harvest.OreComponent;
import javafx.scene.layout.AnchorPane;

public class OreGood implements Good {
    @Override
    public String isGood() {
        return "ORE";
    }

    @Override
    public AnchorPane getGood() {
        return new OreComponent();
    }
}
