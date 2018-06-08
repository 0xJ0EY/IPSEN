package client.source.controllers;

import client.source.Client;
import client.source.components.harvest.PerkComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.Harvest;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HarvestController implements ControllerInterface {

    @FXML private Parent root;
    @FXML private FlowPane buildingContainer;

    private VillagerActionInterface action;

    private Harvest harvest;
    private ArrayList<PerkComponent> perkComponents = new ArrayList<>();
    protected Client client;

    @Override
    public Parent show() throws RemoteException{
        this.updateBuildingsView();

        return root;

    }

    private void updateBuildingsView() throws RemoteException {
        for (int i = 0; i < this.client.getGameClient().getPlayer().getPlayerBoard().getHarvestBuildings().size(); i ++){
            perkComponents.add(new PerkComponent(this.client.getGameClient().getPlayer().getPlayerBoard().getHarvestBuildings().get(i), this));
            buildingContainer.getChildren().add(perkComponents.get(i));

        }

    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    public void confirmSelection() throws RemoteException{
        for (int i = 0; i < perkComponents.size(); i++){
            if (perkComponents.get(i).isSelected()){
                client.getGameClient().getPlayer().getPlayerBoard().getGoods().add(this.client.getGameClient().getPlayer().getPlayerBoard().getHarvestBuildings().get(i).getHarvastable().harvestGood());

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

    public void setHarvest(Harvest harvest){
        this.harvest = harvest;
    }

}
