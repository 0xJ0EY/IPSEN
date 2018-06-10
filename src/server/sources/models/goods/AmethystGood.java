package server.sources.models.goods;

import client.source.components.harvest.AmethystComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class AmethystGood implements Good {

    @Override
    public String getBackground() {
        return "amethyst.png";
    }

}
