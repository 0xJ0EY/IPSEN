package client.source.controllers;

import client.source.Client;
import client.source.components.villager.SelectableVillagerComponent;
import client.source.factories.AllVillagerSelectionFactory;
import client.source.factories.MultipleSelectionFactory;
import client.source.factories.VillagerSelectionComponentFactory;
import client.source.factories.VillagerSelectionFactory;
import client.source.strategies.RequestStrategy;
import client.source.strategies.VillagerSelectionStrategy;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.notifications.CancelNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VillagerSelectionController implements ControllerInterface {

    @FXML private Parent root;

    @FXML private FlowPane villagerContainer;

    private VillagerActionInterface action;

    protected ArrayList<VillagerInterface> villagers;

    protected Client client;

    private ArrayList<SelectableVillagerComponent> villagerComponents;

    private VillagerSelectionFactory villagerFactory;

    private VillagerSelectionStrategy strategy;

    private VillagerSelectionComponentFactory componentFactory;

    public Parent show() {

        this.retrieveVillagers();
        this.updateVillagersView();

        return this.root;
    }


    private void retrieveVillagers() {
        this.villagers = villagerFactory.getVillagerList();
    }

    private void updateVillagersView() {

        this.villagerComponents = new ArrayList<>();
        this.villagerContainer.getChildren().clear();

        for (VillagerInterface villager : this.villagers) {
            SelectableVillagerComponent villagerComponent = componentFactory.createComponent();
            villagerComponent.setModel(villager);
            villagerComponent.load();

            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    @FXML
    private void onClickSelect() {

        ArrayList<VillagerInterface> selected = this.getSelectedVillagers();

        this.action.setSelectedVillagers(selected);

        this.strategy.execute(this.client.getGameClient(), this.action);

    }

    @FXML
    private void onClickCancel() {
        // Just show the main screen
        this.client.showMain();
    }

    public ArrayList<VillagerInterface> getSelectedVillagers() {
        ArrayList<VillagerInterface> selected = new ArrayList<VillagerInterface>();

        for (SelectableVillagerComponent villagerComponent : this.villagerComponents) {

            if (villagerComponent.isSelected()) {
                selected.add(villagerComponent.getVillager());
            }
            
        }

        return selected;
    }

    public ArrayList<SelectableVillagerComponent> getSelectedVillagerComponents() {
        ArrayList<SelectableVillagerComponent> villagers = new ArrayList<SelectableVillagerComponent>();

        for (SelectableVillagerComponent villagerComponent : this.villagerComponents) {
            if (villagerComponent.isSelected()) villagers.add(villagerComponent);
        }

        return villagers;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVillagerFactory(VillagerSelectionFactory villagerFactory) {
        this.villagerFactory = villagerFactory;
        this.villagerFactory.setClient(this.client);
    }

    public void setComponentFactory(VillagerSelectionComponentFactory componentFactory) {
        componentFactory.setController(this);
        this.componentFactory = componentFactory;
    }

    public void setVillagerAction(VillagerActionInterface action) {
        this.action = action;
    }

    public void setStrategy(VillagerSelectionStrategy strategy) {
        this.strategy = strategy;
    }
}
