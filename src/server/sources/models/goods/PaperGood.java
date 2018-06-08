package server.sources.models.goods;

import client.source.components.harvest.PaperComponent;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class PaperGood implements Good, Serializable {

    @Override
    public String isGood() {
        return "PAPER";
    }

    @Override
    public AnchorPane getGood() {
        return new PaperComponent();
    }
}
