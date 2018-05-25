p qackage server.sources.models;

import java.util.ArrayList;

public class VillagerFactory {

    //TODO: afmaken methods
    public Villager createVillager(ArrayList<Lanterns> lanterns){
        Villager villager = new Villager();
        return villager;
    }

    public BuilderVillager createBuilder(ArrayList<Lanterns> lanterns){
        BuilderVillager villager = new BuilderVillager();
        return villager;
    }

    public TrainerVillager createTrainer(ArrayList<Lanterns> lanterns){
        TrainerVillager villager = new TrainerVillager();
        return villager;
    }

    public AllroundVillager createAllround(ArrayList<Lanterns> lanterns){
        AllroundVillager villager = new AllroundVillager();
        return villager;
    }
}
