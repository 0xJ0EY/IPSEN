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

    /**
     * Loads villager selection view.
     * @return loaded villager_selection.FXML
     * @author Jan Douwe Sminia
     */
    public Parent show() {

        this.retrieveVillagers();
        this.updateVillagersView();

        return this.root;
    }

    /**
     * Retrieves all villagers from villagersfactory.
     * @author Jan Douwe Sminia
     */
    private void retrieveVillagers() {
        this.villagers = villagerFactory.getVillagerList();
    }

    /**
     * Updates villagerscontainer
     * @author Jan Douwe Sminia
     */
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

    /**
     * For selecting villagers in order to perform action
     * @author Jan Douwe Sminia
     */
    @FXML
    private void onClickSelect() {

        ArrayList<VillagerInterface> selected = this.getSelectedVillagers();

        this.action.setSelectedVillagers(selected);

        this.strategy.execute(this.client.getGameClient(), this.action);

    }

    /**
     * Cancels the action made by the player.
     * @author Jan Douwe Sminia
     */
    @FXML
    private void onClickCancel() {
        // Just show the main screen
        this.client.showMain();
    }

    /**
     * Getting selected villagers.
     * @return An arraylist of selected villagers
     * @author Jan Douwe Sminia
     */
    public ArrayList<VillagerInterface> getSelectedVillagers() {
        ArrayList<VillagerInterface> selected = new ArrayList<VillagerInterface>();

        for (SelectableVillagerComponent villagerComponent : this.villagerComponents) {

            if (villagerComponent.isSelected()) {
                selected.add(villagerComponent.getVillager());
            }
            
        }

        return selected;
    }

    /**
     * Getting selected villagers components.
     * @return components
     * @author Jan Douwe Sminia
     */
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

    /**
     * For setting villagersfactory.
     * @param villagerFactory crucial for creating villagers before the application starts.
     * @author Jan Douwe Sminia
     */
    public void setVillagerFactory(VillagerSelectionFactory villagerFactory) {
        this.villagerFactory = villagerFactory;
        this.villagerFactory.setClient(this.client);
    }

    /**
     * Setting a component factory.
     * @param componentFactory
     * @author Joey de Ruiter
     */
    public void setComponentFactory(VillagerSelectionComponentFactory componentFactory) {
        componentFactory.setController(this);
        this.componentFactory = componentFactory;
    }

    /**
     * Setting a villager action.
     * @param action
     * @author Joey de Ruiter
     */
    public void setVillagerAction(VillagerActionInterface action) {
        this.action = action;
    }

    /**
     * Setting a strategy for accompaning villager selection.
     * @param strategy
     * @author Joey de Ruiter
     */
    public void setStrategy(VillagerSelectionStrategy strategy) {
        this.strategy = strategy;
    }
}
