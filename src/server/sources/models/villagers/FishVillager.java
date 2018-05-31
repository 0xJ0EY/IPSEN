package server.sources.models.villagers;

import client.source.components.villager.TypeBuilderComponent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class FishVillager extends Villager implements Buildable {

    public FishVillager(ArrayList<Lantern> lanterns, boolean injured, boolean tired) {
        super(lanterns, injured, tired);
    }

    //TODO: implenting build
    @Override
    public void build() {

    }

    @Override
    public AnchorPane getType() {
        return new TypeBuilderComponent();
    }
}
