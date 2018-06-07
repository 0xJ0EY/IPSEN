package client.source.controllers;

import client.source.Client;
import client.source.components.harvest.PerkComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.buildings.Building;

import java.util.ArrayList;

public class HarvestController implements ControllerInterface {

    @FXML private Parent root;
    @FXML private FlowPane buildingContainer;

    private VillagerActionInterface action;

    protected ArrayList<Building> harvestBuildings;
    protected ArrayList<PerkComponent> perkComponents;
    protected Client client;

    @Override
    public Parent show() {
        this.updateBuildingsView();

        return root;

    }

    public void setBuildings(ArrayList<Building> harvestBuidings){
        this.harvestBuildings = harvestBuidings;

    }

    private void updateBuildingsView(){
        for (int i = 0; i < this.harvestBuildings.size(); i ++){
            perkComponents.add(new PerkComponent(this.harvestBuildings.get(i), this));
            buildingContainer.getChildren().add(perkComponents.get(i));

        }

    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    public void confirmSelection(){
        for (int i = 0; i < perkComponents.size(); i++){
            if (perkComponents.get(i).isSelected()){
                perkComponents.get(i);

            }

        }

    }

    public void onlyOneTrue(PerkComponent component){
        for (int i = 0; i < buildingContainer.getChildren().size(); i++){
            perkComponents.get(i).setFalse();

        }
        component.setTrue();

    }

    public HarvestController getController(){
        return this;
    }

}
