package client.source.controllers;

import client.source.Client;
import client.source.components.building.BuildingComponent;
import client.source.components.good.AdvancementGoodComponent;
import client.source.components.good.SelectableGoodComponent;
import client.source.components.villager.VillagerComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import server.sources.interfaces.BuildingInterface;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.buildings.Building;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Class that acts as an intermediary between the aboveview and the model.
 * Created by Joey de Ruiter.
 */
public class AboveController implements Observable {

    @FXML private Parent root;

    @FXML private FlowPane activeVillagers;
    @FXML private FlowPane injuredVillagers;
    @FXML private FlowPane tiredVillagers;
    @FXML private FlowPane goods;

    @FXML private HBox buildings;
    @FXML private HBox advancementTracker;

    @FXML private Label labelCoins;
    @FXML private Label labelCiders;
    @FXML private Label labelPotions;
    @FXML private Label labelBeds;
    @FXML private Label labelIncome;

    @FXML private Pane colourPane;

    private PlayerBoardInterface playerBoard;

    private Client client;

    /**
     * For setting a client
     * @param client Client
     * @author Joey de Ruiter
     */
    public void registerClient(Client client) {
        this.client = client;
        this.client.playerBoardObserver.attach(this);
    }

    /**
     * This is for observing any updates after ending turns and rounds or performing actions made by a player.
     * @author Joey de Ruiter
     */
    @Override
    public void updateObserver() {
        this.playerBoard = this.client.playerBoardObserver.getState();

        this.updateBeds();
        this.updateCiders();
        this.updatePotions();
        this.updateIncome();
        this.updateCoins();
        this.updateVillagers();
        this.updateBuildings();
        this.updateGoods();
        this.updateAdvancementTracker();
        this.updatePlayerColour();
    }

    private void updatePlayerColour() {

        String hex = "";

        try {
            hex = client.getGameClient().getPlayer().getColour().getHex();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.colourPane.setBackground(
                new Background(
                        new BackgroundFill(Color.web(hex), CornerRadii.EMPTY, Insets.EMPTY)
                )
        );


    }

    /**
     * This is for updating amount of beds.
     * @author Joey de Ruiter
     */
    private void updateBeds() {
        int count = 0;

        try {
            count = this.playerBoard.getBeds();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.labelBeds.setText(String.format(count == 1 ? "%s bed" : "%s beds", count));
    }

    /**
     * Of course, for updating amount of ciders that a player has in its possession after performing an action.
     * @author Joey de Ruiter
     */
    private void updateCiders() {
        int count = 0;

        try {
            count = this.playerBoard.getCiders();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.labelCiders.setText(String.format(count == 1 ? "%s cider" : "%s ciders", count));

    }

    /**
     * For updating amount of potions that a player has in its possession after performing an action or when the round ends.
     * @author Joey de Ruiter
     */
    private void updatePotions() {
        int count = 0;

        try {
            count = this.playerBoard.getPotions();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.labelPotions.setText(String.format(count == 1 ? "%s potion" : "%s potions", count));
    }

    /**
     * For updating amount of income that a player has in its possession after performing an action or when the round ends.
     * @author Joey de Ruiter
     */
    private void updateIncome() {
        int count = 0;

        try {
            count = this.playerBoard.getAdvancementTracker().calculateCoins();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.labelIncome.setText(String.format(count == 1 ? "+%s coin" : "+%s coins", count));
    }

    /**
     * For updating amount of coins that a player has in its possession after performing an action or when the round ends.
     * @author Joey de Ruiter
     */
    private void updateCoins() {
        int count = 0;

        try {
            count = this.playerBoard.getCoins();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.labelCoins.setText(String.format(count == 1 ? "%s coin" : "%s coins", count));
    }

    /**
     * For updating a container of villagers that a player has chosen after performing an action or when the round ends it will return to default.
     * @author Joey de Ruiter
     */
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

    /**
     * For updating a container of buildings that a player has bought after performing an action 'build' or when the round ends it will return to default.
     * @author Joey de Ruiter
     */
    private void updateBuildings() {
        this.buildings.getChildren().clear();

        try {
            ArrayList<BuildingInterface> buildings = playerBoard.getBuildings();

            for (BuildingInterface building : buildings) {
                BuildingComponent buildingComponent = new BuildingComponent();
                buildingComponent.setModel(building);
                buildingComponent.load();

                this.buildings.getChildren().add(buildingComponent);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * For updating a container of available goods
     * @author Joey de Ruiter
     */
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

    /**
     * For updating a container of advancement tracker with goods, points, etc.
     * @author Joey de Ruiter
     */
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

    }
}
