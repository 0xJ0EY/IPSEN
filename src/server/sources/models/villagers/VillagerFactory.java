package server.sources.models.villagers;

import java.util.ArrayList;

public class VillagerFactory {

    //TODO: afmaken methods
    public Villager createVillager(ArrayList<Lanterns> lanterns){
        Villager villager = new Villager(lanterns, false, false );
        return villager;
    }

    public BuilderVillager createBuilder(ArrayList<Lanterns> lanterns){
        BuilderVillager villager = new BuilderVillager(lanterns, false, false);
        return villager;
    }

    public TrainerVillager createTrainer(ArrayList<Lanterns> lanterns){
        TrainerVillager villager = new TrainerVillager(lanterns, false, false);
        return villager;
    }

    public AllroundVillager createAllround(ArrayList<Lanterns> lanterns){
        AllroundVillager villager = new AllroundVillager(lanterns, false, false);
        return villager;
    }
}
