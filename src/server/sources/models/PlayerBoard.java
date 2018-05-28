package server.sources.models;

import server.sources.models.villagers.Villager;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerBoard implements Serializable {

    private ArrayList<Villager> villagers = new ArrayList<>();

    public Villager getVillager(int index) {
        return villagers.get(index);
    }

    public void addVillager(Villager villager) {
        villagers.add(villager);
    }


}
