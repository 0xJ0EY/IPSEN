package client.source.controllers;

import client.source.Client;
import client.source.components.villager.VillagerComponent;
import client.source.factories.VillagerSelectionFactory;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VillagerSelectionController implements ControllerInterface {

    @FXML private Parent root;

    @FXML private FlowPane villagerContainer;

    private VillagerActionInterface action;

    protected ArrayList<Villager> villagers;

    protected Client client;

    private ArrayList<VillagerComponent> villagerComponents;

    private VillagerSelectionFactory factory;

    public Parent show() {

        this.retrieveVillagers();
        this.updateVillagersView();

        return this.root;
    }


    private void retrieveVillagers() {
        this.villagers = factory.getVillagerList();
    }

    private void updateVillagersView() {

        this.villagerComponents = new ArrayList<>();
        this.villagerContainer.getChildren().clear();

        for (Villager villager : this.villagers) {

            VillagerComponent villagerComponent = new VillagerComponent(villager);
            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    @FXML
    private void onClickSelect() {
        try {
            ArrayList<Villager> selected = this.getSelectedVillagers();

            this.action.setSelectedVillagers(selected);
            this.client.getGameClient().requestAction(this.action);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Villager> getSelectedVillagers() {
        ArrayList<Villager> selected = new ArrayList<>();

        for (VillagerComponent villagerComponent : this.villagerComponents) {

            if (villagerComponent.isSelected()) {
                selected.add(villagerComponent.getVillager());
            }
            
        }

        return selected;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setFactory(VillagerSelectionFactory factory) {
        this.factory = factory;
        this.factory.setClient(this.client);
    }

    public void setVillagerAction(VillagerActionInterface action) {
        this.action = action;
    }
}
