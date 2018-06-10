package server.sources.models.goods;

import client.source.components.harvest.OreComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class OreGood implements Good {

    @Override
    public String getBackground() {
        return "ore.png";
    }

}
