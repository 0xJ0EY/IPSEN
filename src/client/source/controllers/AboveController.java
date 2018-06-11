package client.source.controllers;

import client.source.Client;
import client.source.components.building.BuildingComponent;
import client.source.components.good.AdvancementGoodComponent;
import client.source.components.good.SelectableGoodComponent;
import client.source.components.villager.VillagerComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.buildings.Building;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public class AboveController implements Observable {

    @FXML private Parent root;

    @FXML private FlowPane activeVillagers;
    @FXML private FlowPane injuredVillagers;
    @FXML private FlowPane tiredVillagers;
    @FXML private HBox buildings;

    @FXML private HBox advancementTracker;

    @FXML private FlowPane goods;
    @FXML private Text points;

    private PlayerBoardInterface playerBoard;

    private Client client;

    /**
     * For setting a client
     * @param client
     */
    public void registerClient(Client client) {
        this.client = client;
        this.client.playerBoardObserver.attach(this);
    }

    @Override
    public void updateObserver() {
        this.playerBoard = this.client.playerBoardObserver.getState();

        this.updateBeds();
        this.updateCiders();
        this.updatePotions();
        this.updateVillagers();
        this.updateBuildings();
        this.updateGoods();
        this.updateAdvancementTracker();
    }

    private void updateBeds() {

    }

    private void updateCiders() {

    }

    private void updatePotions() {

    }

    private void updateVillagers() {

        this.activeVillagers.getChildren().clear();
        this.injuredVillagers.getChildren().clear();
        this.tiredVillagers.getChildren().clear();

        try {
            ArrayList<VillagerInterface> villagers = playerBoard.listVillagers();

            for (VillagerInterface villager : villagers) {

                VillagerComponent villagerComponent = new VillagerComponent();
                villagerComponent.setModel(villager);
                villagerComponent.load();

                switch (villager.getState()) {
                    case USABLE:
                        this.activeVillagers.getChildren().add(villagerComponent);
                        break;

                    case TIRED:
                        this.tiredVillagers.getChildren().add(villagerComponent);
                        break;

                    case INJURED:
                        this.injuredVillagers.getChildren().add(villagerComponent);
                        break;
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updateBuildings() {
        this.buildings.getChildren().clear();

        try {
            ArrayList<Building> buildings = playerBoard.getBuildings();

            for (Building building : buildings) {
                BuildingComponent buildingComponent = new BuildingComponent();
                buildingComponent.setModel(building);
                buildingComponent.load();

                this.buildings.getChildren().add(buildingComponent);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // TODO: Move to its own controller?
    private void updateGoods() {
        this.goods.getChildren().clear();

        try {
            ArrayList<Good> goods = playerBoard.getGoods();

            int index = 0;

            for (Good good : goods) {
                SelectableGoodComponent goodComponent = new SelectableGoodComponent();
                goodComponent.setModel(good);
                goodComponent.setClient(this.client);
                goodComponent.setIndex(index++);

                goodComponent.load();

                this.goods.getChildren().add(goodComponent);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // TODO: Move to its own controller?
    private void updateAdvancementTracker() {
        this.advancementTracker.getChildren().clear();

        try {
            Map<Good, Integer> tokens = playerBoard.getAdvancementTracker().getTokens();

            int index = 0;

            for (Map.Entry<Good, Integer> entry : tokens.entrySet()) {
                AdvancementGoodComponent goodComponent = new AdvancementGoodComponent();
                goodComponent.setModel(entry.getKey());
                goodComponent.setAmount(entry.getValue());
                goodComponent.setClient(this.client);
                goodComponent.setIndex(index++);

                goodComponent.load();

                this.advancementTracker.getChildren().add(goodComponent);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // TODO: Fill all other 8 spaces
    }
}
