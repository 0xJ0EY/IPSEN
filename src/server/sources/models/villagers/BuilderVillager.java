package server.sources.models.villagers;

import client.source.components.villager.TypeBuilderComponent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class BuilderVillager extends Villager implements Buildable{

    public BuilderVillager(ArrayList<Lantern> lanterns, boolean injured, boolean tired) {
        super(lanterns, injured, tired);
    }

    @Override
    public void build() {

    }

    @Override
    public AnchorPane getType() {
        return new TypeBuilderComponent();
    }
}
