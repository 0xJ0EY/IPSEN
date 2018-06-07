package server.sources.interfaces;

import server.sources.models.villagers.Villager;

import java.util.ArrayList;

public interface VillagerActionInterface extends ActionInterface {

    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers);

}
