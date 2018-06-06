package server.sources.models.goods;

import client.source.components.harvest.ClayPotComponent;
import javafx.scene.layout.AnchorPane;

public class PotGood implements Good {
    @Override
    public String isGood() {
        return "POT";
    }

    @Override
    public AnchorPane getGood() {
        return new ClayPotComponent();
    }
}
