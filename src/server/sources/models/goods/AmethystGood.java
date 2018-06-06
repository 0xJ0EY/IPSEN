package server.sources.models.goods;

import client.source.components.harvest.AmethystComponent;
import javafx.scene.layout.AnchorPane;

public class AmethystGood implements Good {
    @Override
    public String isGood() {
        return "AMETHYST";
    }

    @Override
    public AnchorPane getGood() {
        return new AmethystComponent();
    }
}
