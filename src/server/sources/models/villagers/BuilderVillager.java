package server.sources.models.villagers;

import java.util.ArrayList;

public class BuilderVillager extends Villager implements Buildable{

    public BuilderVillager(ArrayList<Lantern> lanterns, boolean injured, boolean tired) {
        super(lanterns, injured, tired);
    }

    @Override
    public void build() {

    }
}
