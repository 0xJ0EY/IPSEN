package client.source.controllers;

import client.source.Client;
import client.source.components.building.BuildingComponent;
import client.source.components.building.SelectableBuildingComponent;
import client.source.components.building.SingleSelectableBuildingComponent;
import client.source.components.villager.SelectableVillagerComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import server.sources.actions.BuildAction;
import server.sources.actions.CancelAction;
import server.sources.actions.EndTurnAction;
import server.sources.actions.RefreshHousesAction;
import server.sources.interfaces.*;
import server.sources.models.buildings.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that acts as an intermediary between the buildview and the model.
 * Created by robin on 1-6-2018.
 */
public class BuildController implements SelectableControllerInterface, Observable {

    private boolean buying = false;

    private Client client;
    private MarketInterface market;

    private ArrayList<VillagerInterface> usedTrainerVillagers;

    @FXML private Parent root;
    @FXML private Button refreshButton;
    @FXML private Button cancelButton;
    @FXML private Button buyButton;

    /**
     * Here are all buildingcontainers declared to store building cards in building market.
     */
    @FXML private HBox housesContainer;
    @FXML private HBox outpostsContainer;
    @FXML private HBox keyHousesContainer;
    @FXML private HBox starHousesContainer;

    @FXML private Text message;

    private ArrayList<MarketHouse> houses = new ArrayList<MarketHouse>();
    private ArrayList<MarketOutpost> outposts = new ArrayList<MarketOutpost>();
    private ArrayList<MarketKeyHouse> keyHouses = new ArrayList<MarketKeyHouse>();
    private ArrayList<MarketStarHouse> starHouses = new ArrayList<MarketStarHouse>();

    private ArrayList<SelectableBuildingComponent> houseComponents = new ArrayList<SelectableBuildingComponent>();
    private ArrayList<SelectableBuildingComponent> outpostComponents = new ArrayList<SelectableBuildingComponent>();
    private ArrayList<SelectableBuildingComponent> keyHouseComponents = new ArrayList<SelectableBuildingComponent>();
    private ArrayList<SelectableBuildingComponent> starHouseComponents = new ArrayList<SelectableBuildingComponent>();

    private PlayerInterface target;

    private Thread messageThread;

    /**
     * Load all the buildings required for the building controller.
     * @author Joey de Ruiter
     */
    public void load() {
        try {
            this.houses = market.listAvailableHouses();
            this.outposts = market.listAvailableOutposts();
            this.keyHouses = market.listAvailableKeyHouses();
            this.starHouses = market.listAvailableStarHouses();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.createHouseComponents();
        this.createOutpostComponents();
        this.createKeyHouseComponents();
        this.createStarHouseComponets();

        // Show or hide cancel and buy buttons
        this.target = this.client.turnObserver.getState();

        // No target, so its not even worth going here
        if (target == null) return;

        try {
            boolean turn = target.getGameClient().equals(this.client.getGameClient());
            this.cancelButton.setDisable(!turn);
            this.buyButton.setDisable(!turn);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is for observing any updates after ending turns and rounds or performing actions made by a player.
     * @author Joey de Ruiter
     */
    @Override
    public void updateObserver() {
        this.load();
    }

    /**
     * For creating housecomponents and storing them in a housescontainer in building market.
     * @author Robin Silverio, Joey De Ruiter and Richard Kerkvliet
     */
    private void createHouseComponents() {
        if (this.houses == null || this.houses.size() == 0) return;

        this.housesContainer.getChildren().clear();

        for (BuildingInterface house : this.houses) {
            SingleSelectableBuildingComponent houseComponent = new SingleSelectableBuildingComponent();
            houseComponent.setModel(house);
            houseComponent.setController(this);
            houseComponent.load();

            this.houseComponents.add(houseComponent);
            this.housesContainer.getChildren().add(houseComponent);
        }
    }

    /**
     * For creating outpostcomponents and storing them in an outpostscontainer in building market.
     * @author Robin Silverio, Joey De Ruiter and Richard Kerkvliet
     */
    private void createOutpostComponents() {
        if (this.outposts == null || this.outposts.size() == 0) return;

        this.outpostsContainer.getChildren().clear();

        for (BuildingInterface outpost : this.outposts) {
            SelectableBuildingComponent outpostComponent = new SingleSelectableBuildingComponent();
            outpostComponent.setModel(outpost);
            outpostComponent.setController(this);
            outpostComponent.load();

            this.outpostComponents.add(outpostComponent);
            this.outpostsContainer.getChildren().add(outpostComponent);
        }
    }

    /**
     * For creating keyhousecomponents and storing them in a keyhousescontainer in building market.
     * @author Robin Silverio, Joey De Ruiter and Richard Kerkvliet
     */
    private void createKeyHouseComponents() {
        if (this.keyHouses == null || this.keyHouses.size() == 0) return;

        this.keyHousesContainer.getChildren().clear();

        for (BuildingInterface keyHouse : keyHouses) {
            SelectableBuildingComponent keyhouseComponent = new SingleSelectableBuildingComponent();
            keyhouseComponent.setModel(keyHouse);
            keyhouseComponent.setController(this);
            keyhouseComponent.load();

            this.keyHouseComponents.add(keyhouseComponent);
            this.keyHousesContainer.getChildren().add(keyhouseComponent);
        }
    }

    /**
     * For creating starhousecomponents and storing them in a starhousescontainer in building market.
     * @author Robin Silverio, Joey De Ruiter and Richard Kerkvliet
     */
    private void createStarHouseComponets() {
        if (this.starHouses == null || this.keyHouses.size() == 0) return;

        this.starHousesContainer.getChildren().clear();

        for (BuildingInterface starHouse : this.starHouses) {
            SelectableBuildingComponent starhouseComponent = new SingleSelectableBuildingComponent();
            starhouseComponent.setModel(starHouse);
            starhouseComponent.setController(this);
            starhouseComponent.load();

            this.starHouseComponents.add(starhouseComponent);
            this.starHousesContainer.getChildren().add(starhouseComponent);
        }
    }

    /**
     * For setting a client in a view.
     * @param client the player using this application to play the game
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter and Robin Silverio
     */
    public void setClient(Client client) throws RemoteException {
        this.client = client;

        // Set market
        this.market = this.client.getGameClient().getServer().getGameController().getMarket();
        this.client.marketObserver.attach(this);
    }

    /**
     * For displaying the view.
     * @return a loaded build_market.FXML
     * @author Robin Silverio
     */
    public Parent show() {
        return this.root;
    }

    /**
     * Of course, This is for getting an arraylist of all selectable buildingcomponents from each containers to be loaded in a building market.
     * @return An arraylist of selectable buildings
     * @author Robin Silverio
     */
    public ArrayList<SelectableBuildingComponent> getSelectedBuildingComponents() {
        ArrayList<SelectableBuildingComponent> buildings = new ArrayList<SelectableBuildingComponent>();

        for (SelectableBuildingComponent houseComponent : this.houseComponents) {
            if (houseComponent.isSelected()) buildings.add(houseComponent);
        }

        for (SelectableBuildingComponent outpostComponent : this.outpostComponents) {
            if (outpostComponent.isSelected()) buildings.add(outpostComponent);
        }

        for (SelectableBuildingComponent keyHouseComponent : this.keyHouseComponents) {
            if (keyHouseComponent.isSelected()) buildings.add(keyHouseComponent);
        }

        for (SelectableBuildingComponent starHouseComponent : this.starHouseComponents) {
            if (starHouseComponent.isSelected()) buildings.add(starHouseComponent);
        }

        return buildings;
    }

    /**
     * Performs a click eventhandling when player clicks on cancel button, he will be redirected to aboveview.
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @FXML
    private void onClickCancel() throws RemoteException {
        this.client.showMain();
    }

    /**
     * Performs a click eventhandling when player clicks on buy button, so that he can build a building.
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    @FXML
    private void onClickBuy() throws RemoteException {
        if (!this.canBuy()) return;
        this.disableBuying();

        ArrayList<SelectableBuildingComponent> selectedBuildings = this.getSelectedBuildingComponents();

        if (selectedBuildings.size() < 1) {
            this.showMessage("Please select a building.");
            this.enableBuying();
            return;
        }

        SelectableBuildingComponent selected = selectedBuildings.get(0);

        if (selected.getModel().getCost() > this.target.getPlayerBoard().getCoins()) {
            this.showMessage("Not sufficient funds.");
            this.enableBuying();
            return;
        }

        if (selected.getModel() instanceof Outpost && !target.getPlayerBoard().hasCaveCards()){
            this.showMessage("Explore to get cave cards first");
            this.enableBuying();
            return;
        }

        try {
            BuildingMarketInterface building = (BuildingMarketInterface) selected.getModel();
            building.buy(this.market, this.target.getGameClient());

            //this.client.getGameClient().getPlayer().doAction(new EndTurnAction(this.usedTrainerVillagers));
            this.client.showBuildReward(selected.getModel());
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            this.enableBuying();
        }

    }

    private boolean canBuy() {
        return !this.buying;
    }


    private void enableBuying() {
        this.buying = false;
        this.buyButton.setDisable(false);
    }

    private void disableBuying() {
        this.buying = true;
        this.buyButton.setDisable(true);
    }

    public void showMessage(String message) {
        if (this.messageThread != null && this.messageThread.isAlive()) this.messageThread.interrupt();

        Runnable r = () -> {
            this.message.setText(message);
            this.message.setVisible(true);

            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                System.out.println("Message interrupted");
            } finally {
                this.message.setVisible(false);
            }
        };

        this.messageThread = new Thread(r);
        this.messageThread.start();
    }

    public void setUsedTrainerVillager(ArrayList<VillagerInterface> villagers) {
        this.usedTrainerVillagers = villagers;
    }
}