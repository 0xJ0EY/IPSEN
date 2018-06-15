package client.source.controllers;

import client.source.Client;
import client.source.components.building.SelectableBuildingComponent;
import client.source.components.building.SingleSelectableBuildingComponent;
import client.source.components.harvest.MushroomComponent;
import client.source.components.reward.GoodRewardComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import server.sources.actions.HarvestAction;
import server.sources.interfaces.BuildingInterface;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.buildings.Building;
import server.sources.models.goods.Good;
import server.sources.models.perks.Harvestable;
import server.sources.models.perks.HarvestableGoodPerk;
import server.sources.models.perks.Perk;
import server.sources.models.perks.ReplenishableGoodPerk;
import server.sources.models.stories.rewards.GoodReward;
import server.sources.notifications.NoHarvestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A class that acts as an intermediary between a harvestview and model.
 * Created by Jan Douwe Sminia.
 */
public class HarvestController implements SelectableControllerInterface {

    private Client client;
    private HarvestAction harvest;

    @FXML private Parent root;
    @FXML private HBox buildingContainer;
    @FXML private HBox goodsContainer;

    @FXML private Text message;

    @FXML private Button cancelButton;
    @FXML private Button confirmButton;

    private ArrayList<BuildingInterface> buildings = new ArrayList<BuildingInterface>();
    private ArrayList<SelectableBuildingComponent> buildingComponents = new ArrayList<SelectableBuildingComponent>();
    private ArrayList<GoodRewardComponent> goods = new ArrayList<GoodRewardComponent>();

    private Thread messageThread;

    /**
     * For loading all harvest buildings in harvestview.
     * @author Jan Douwe Sminia
     */
    public void load()  {
        try {
            this.buildings = this.harvest.getTarget().getPlayer().getPlayerBoard().getHarvestBuildings();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.loadButtons();

        this.createBuildingComponents();

        this.createHarvestedGoods();

        this.showMessage("Select a building to harvest.");
    }

    /**
     * For creating buildingcomponents and storing them in a buildingcontainer in harvest view.
     * @author Jan Douwe Sminia
     */
    private void createBuildingComponents() {
        if (this.buildings == null || this.buildings.size() == 0) return;

        this.buildingContainer.getChildren().clear();

        for (BuildingInterface building : this.buildings) {
            SelectableBuildingComponent buildingComponent = new SingleSelectableBuildingComponent();
            buildingComponent.setModel(building);
            buildingComponent.setController(this);
            buildingComponent.load();

            this.buildingComponents.add(buildingComponent);
            this.buildingContainer.getChildren().add(buildingComponent);
        }
    }

    private void createHarvestedGoods() {
        ArrayList<GoodRewardComponent> rewards = this.harvest.getGoods();

        if (rewards == null || rewards.size() == 0) return;

        this.goodsContainer.getChildren().clear();

        for (GoodRewardComponent reward : rewards) {
            reward.load();
            this.goodsContainer.getChildren().add(reward);
        }
    }

    /**
     * For setting a client in harvest view.
     * @param client, a player that uses the application to play game online
     * @throws RemoteException java.rmi.RemoteException
     * @author Jan Douwe Sminia
     */
    public void setClient(Client client) throws RemoteException {
        this.client = client;
    }

    /**
     * Of course, This is for getting an arraylist of all selectable buildingcomponents from each containers to be loaded in a building market.
     * @return An arraylist of selectable buildings
     * @author Jan Douwe Sminia
     */
    @Override
    public ArrayList<SelectableBuildingComponent> getSelectedBuildingComponents() {
        ArrayList<SelectableBuildingComponent> buildings = new ArrayList<SelectableBuildingComponent>();

        for (SelectableBuildingComponent buildingComponent : this.buildingComponents) {
            if (buildingComponent.isSelected()) buildings.add(buildingComponent);
        }

        return buildings;
    }

    /**
     * For setting a harvest action
     * @param harvest HarvestAction
     * @author Jan Douwe Sminia
     */
    public void setHarvest(HarvestAction harvest) {
        this.harvest = harvest;
    }

    /**
     * Confirms the harvest action after selecting buildings to harvest goods.
     * @throws RemoteException java.rmi.RemoteException
     * @author Jan Douwe Sminia
     */
    @FXML
    public void onClickConfirm() throws RemoteException {
        ArrayList<SelectableBuildingComponent> selected = this.getSelectedBuildingComponents();

        if (selected == null || selected.size() == 0 || selected.size() > 1) {
            return;
        }

        SelectableBuildingComponent building = selected.get(0);

        building.getModel().harvest(this.client.getGameClient());

        this.goods = this.harvest.getGoods();

        for (Perk perk:building.getModel().listPerks()) {
            GoodRewardComponent goodRewardComponent = new GoodRewardComponent();

            if (perk instanceof HarvestableGoodPerk){
                Good good = ((HarvestableGoodPerk) perk).getGood();

                GoodReward goodReward = new GoodReward(good, 1);

                goodRewardComponent.setModel(goodReward);

                this.goods.add(goodRewardComponent);
            } else if (perk instanceof ReplenishableGoodPerk){
                Good good = ((ReplenishableGoodPerk) perk).getGood();

                GoodReward goodReward = new GoodReward(good, 1);

                goodRewardComponent.setModel(goodReward);

                this.goods.add(goodRewardComponent);
            }
        }

        this.showMessage("Harvested good.");

        this.harvest.setGoods(this.goods);
        for (GoodRewardComponent good:goods) {
            System.out.println( good.getModel().isGood());
        }
        this.client.getGameClient().requestAction(this.harvest);
    }

    /**
     * Displays a harvestview.
     * @return a loaded harvest.FXML
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public Parent show() throws RemoteException{
        return root;
    }

    @FXML
    private void cancelAction(){
        this.client.showMain();
    }

    private void loadButtons() {
        boolean turn = !this.hasTurn();

        this.cancelButton.setDisable(turn);
        this.confirmButton.setDisable(turn);
    }

    public void showMessage(String message) {
        if (this.messageThread != null && this.messageThread.isAlive()) this.messageThread.interrupt();

        Runnable r = () -> {
            this.message.setText(message);
            this.message.setVisible(true);

            try {
                Thread.sleep(1790);
            } catch (InterruptedException e) {
                System.out.println("Message interrupted");
            } finally {
                this.message.setVisible(false);
            }
        };

        this.messageThread = new Thread(r);
        this.messageThread.start();
    }

    @Override
    public boolean hasTurn() {
        try {
            return this.harvest.getTarget().equals(client.getGameClient());
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
