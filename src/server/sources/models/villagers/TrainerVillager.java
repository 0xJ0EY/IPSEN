package server.sources.models.villagers;

import java.util.ArrayList;

public class TrainerVillager extends Villager implements Trainable {

    public TrainerVillager(ArrayList<Lanterns> lanterns, boolean injured, boolean tired) {
        super(lanterns, injured, tired);
    }

    @Override
    public void train() {

    }
}
