package server.sources.models.goods;

import client.source.components.harvest.PaperComponent;
import javafx.scene.layout.AnchorPane;

public class PaperGood implements Good {
    @Override
    public String isGood() {
        return "PAPER";
    }

    @Override
    public AnchorPane getGood() {
        return new PaperComponent();
    }
}
