package server.sources.models.villagers;

import client.source.components.villager.TypeAllroundComponent;
import client.source.components.villager.TypeDefaultComponent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class AllroundVillager extends Villager implements Trainable, Buildable {

    public AllroundVillager(ArrayList<Lantern> lanterns, boolean injured, boolean tired) {
        super(lanterns, injured, tired);
    }

    //TODO: implementing build
    public void build() {

    }

    //TODO: implenting train
    public void train() {

    }

    @Override
    public AnchorPane getType() {
        return new TypeAllroundComponent();
    }

}
