package server.sources.models;

import server.sources.interfaces.VillagerInterface;
import server.sources.models.buildings.Building;

import java.io.Serializable;
import java.util.ArrayList;

public class Harvest implements Serializable {
    private ArrayList<Building> harvestBuildings;
    private ArrayList<VillagerInterface> selectedVillagers;
    private int currentStep;

    public Harvest(ArrayList<VillagerInterface> v, int c){
        this.currentStep = c;
        this.selectedVillagers = v;

    }

    public ArrayList<VillagerInterface> getSelectedVillagers() {
        return selectedVillagers;

    }

    public int getCurrentStep() {
        return currentStep;

    }
}
