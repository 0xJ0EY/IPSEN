package client.source.controllers;

import client.source.Client;
import client.source.components.building.BuildingComponent;
import client.source.components.villager.VillagerComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import server.sources.actions.RefreshHousesAction;
import server.sources.interfaces.MarketInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.buildings.KeyHouse;
import server.sources.models.buildings.MarketHouse;
import server.sources.models.buildings.MarketOutpost;
import server.sources.models.buildings.StarHouse;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class MarketController implements Observable {

    private Client client;

    @FXML private Parent root;
    @FXML private HBox villagers;
    @FXML private HBox houses;
    @FXML private HBox keyhouses;
    @FXML private HBox starhouses;
    @FXML private HBox outposts;

    private MarketInterface market;

    /**
     * For setting a client
     *
     * @param client
     * @author Joey de Ruiter
     */
    public void registerClient(Client client) {
        this.client = client;

        this.client.marketObserver.attach(this);
    }

    /**
     * Refreshes buildings in market
     * @author Joey de Ruiter
     */
    public void clickRefreshHouses() {

        try {
            client.getGameClient().requestAction(new RefreshHousesAction());

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    /**
     * Observes all updates in marketview.
     * @author Joey de Ruiter
     */
    @Override
    public void updateObserver() {
        this.market = this.client.marketObserver.getState();

        try {
            this.createVillagerComponents();

            this.createHouseComponents();
            this.createKeyHouseComponents();
            this.createStarHouseComponents();
            this.createOutpostsHouseComponents();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Create villagerscomponents so that can be stocked in market in case that a player wants to buy
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    private void createVillagerComponents() throws RemoteException {
        this.villagers.getChildren().clear();

        for (VillagerInterface villagerInterface : this.market.listAvailableVillagers()) {
            VillagerComponent villagerComponent = new VillagerComponent();
            villagerComponent.setModel(villagerInterface);
            villagerComponent.load();

            this.villagers.getChildren().add(villagerComponent);
        }
    }

    /**
     * For creating housecomponents and storing them in a housescontainer in market.
     * @author Richard Kerkvliet
     */
    private void createHouseComponents() throws RemoteException {
        this.houses.getChildren().clear();

        for (MarketHouse marketHouse : this.market.listAvailableHouses()) {
            BuildingComponent buildingComponent = new BuildingComponent();
            buildingComponent.setModel(marketHouse);
            buildingComponent.load();

            this.houses.getChildren().add(buildingComponent);
        }
    }


    /**
     * For creating keyhousecomponents and storing them in a keyhousescontainer in market.
     * @author Richard Kerkvliet
     */
    private void createKeyHouseComponents() throws RemoteException {
        this.keyhouses.getChildren().clear();

        for (KeyHouse keyhouses : this.market.listAvailableKeyHouses()) {
            BuildingComponent buildingComponent = new BuildingComponent();
            buildingComponent.setModel(keyhouses);
            buildingComponent.load();

            this.keyhouses.getChildren().add(buildingComponent);
        }
    }

    /**
     * For creating starhousecomponents and storing them in a starhousescontainer in market.
     * @author Robin Silverio
     */
    private void createStarHouseComponents() throws RemoteException {
        this.starhouses.getChildren().clear();

        for (StarHouse starHouse : this.market.listAvailableStarHouses()) {
            BuildingComponent buildingComponent = new BuildingComponent();
            buildingComponent.setModel(starHouse);
            buildingComponent.load();

            this.starhouses.getChildren().add(buildingComponent);
        }
    }

    /**
     * For creating outpostcomponents and storing them in a outpostscontainer in market.
     * @author Robin Silverio
     */
    private void createOutpostsHouseComponents() throws RemoteException {
        this.outposts.getChildren().clear();

        for (MarketOutpost marketOutpost : this.market.listAvailableOutposts()) {
            BuildingComponent buildingComponent = new BuildingComponent();
            buildingComponent.setModel(marketOutpost);
            buildingComponent.load();

            this.outposts.getChildren().add(buildingComponent);
        }
    }
}