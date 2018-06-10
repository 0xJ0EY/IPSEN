package client.source.controllers;

import client.source.Client;
import client.source.components.building.SelectableBuildingComponent;
import client.source.components.building.SingleSelectableBuildingComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.actions.HarvestAction;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.buildings.Building;
import server.sources.models.goods.Good;
import server.sources.models.perks.Harvestable;
import server.sources.models.perks.HarvestableGoodPerk;
import server.sources.models.perks.Perk;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HarvestController implements SelectableControllerInterface {

    private Client client;
    private PlayerBoardInterface playerBoard;

    private HarvestAction harvest;

    @FXML private Parent root;
    @FXML private FlowPane buildingContainer;

    private VillagerActionInterface action;

    private ArrayList<Building> buildings = new ArrayList<Building>();
    private ArrayList<SelectableBuildingComponent> buildingComponents = new ArrayList<SelectableBuildingComponent>();

    public void load()  {
        try {
            this.buildings = playerBoard.getHarvestBuildings();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.createBuildingComponents();
    }

    private void createBuildingComponents() {
        if (this.buildings == null || this.buildings.size() == 0) return;

        this.buildingContainer.getChildren().clear();

        for (Building building : this.buildings) {
            SelectableBuildingComponent buildingComponent = new SingleSelectableBuildingComponent();
            buildingComponent.setModel(building);
            buildingComponent.setController(this);
            buildingComponent.load();

            this.buildingComponents.add(buildingComponent);
            this.buildingContainer.getChildren().add(buildingComponent);
        }
    }

    public void setClient(Client client) throws RemoteException {
        this.client = client;

        // Set market
        this.playerBoard = this.client.getGameClient().getPlayer().getPlayerBoard();
    }

    @Override
    public ArrayList<SelectableBuildingComponent> getSelectedBuildingComponents() {
        ArrayList<SelectableBuildingComponent> buildings = new ArrayList<SelectableBuildingComponent>();

        for (SelectableBuildingComponent buildingComponent : this.buildingComponents) {
            if (buildingComponent.isSelected()) buildings.add(buildingComponent);
        }

        return buildings;
    }

    public void setHarvest(HarvestAction harvest) {
        this.harvest = harvest;
    }

    @FXML
    public void onClickConfirm() throws RemoteException {
        ArrayList<SelectableBuildingComponent> selected = this.getSelectedBuildingComponents();

        if (selected == null || selected.size() == 0 || selected.size() > 1) {
            return;
        }

        SelectableBuildingComponent building = selected.get(0);

        for (Perk perk : building.getModel().listPerks()) {
            if (perk instanceof Harvestable && ((Harvestable)perk).canHarvest()) {
                HarvestableGoodPerk harvestableGoodPerk = (HarvestableGoodPerk) perk;

                Good good = harvestableGoodPerk.getGood();
                harvestableGoodPerk.harvest();

                this.client.getGameClient().getPlayer().getPlayerBoard().addGood(good);
            }
        }

        this.client.getGameClient().requestAction(harvest);
    }

    @Override
    public Parent show() throws RemoteException{
        return root;
    }
}
