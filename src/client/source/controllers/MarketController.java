package client.source.controllers;

import client.source.Client;
import client.source.components.building.BuildingComponent;
import client.source.components.villager.VillagerComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import server.sources.actions.RefreshHousesAction;
import server.sources.interfaces.BuildingInterface;
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
    @FXML private GridPane villagers;
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
     * @author Richard Kerkvliet
     */
    public void clickRefreshHouses() {

        try {
            if(client.getGameClient().getPlayer().getPlayerBoard().getCoins() == 0){
                Alert dialog = new Alert(Alert.AlertType.WARNING);
                dialog.setContentText("You do not have enough coins");
                dialog.show();
            }else {
                client.getGameClient().requestAction(new RefreshHousesAction());
                client.getGameClient().getPlayer().getPlayerBoard().payCoin(1);
            }
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

    @FXML
    public void sellGood() throws RemoteException{
        this.client.showSellableGoods(client.getGameClient());

    }

    @FXML
    public void buyGood() throws RemoteException{
        this.client.showBuyableGoods(client.getGameClient());

    }

    /**
     * Create villagerscomponents so that can be stocked in market in case that a player wants to buy
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    private void createVillagerComponents() throws RemoteException {
        this.villagers.getChildren().clear();

        VillagerInterface[] villagerInterfaces = this.market.listAvailableVillagers();

        for (int i = 0; i < villagerInterfaces.length; i++) {
            if (villagerInterfaces[i] == null) continue;

            VillagerInterface villagerInterface = villagerInterfaces[i];

            VillagerComponent villagerComponent = new VillagerComponent();
            villagerComponent.setModel(villagerInterface);
            villagerComponent.load();

            GridPane.setColumnIndex(villagerComponent, i);
            this.villagers.getChildren().add(villagerComponent);
        }
    }

    /**
     * For creating housecomponents and storing them in a housescontainer in market.
     * @author Richard Kerkvliet
     */
    private void createHouseComponents() throws RemoteException {
        this.houses.getChildren().clear();

        for (BuildingInterface marketHouse : this.market.listAvailableHouses()) {
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

        for (BuildingInterface keyhouses : this.market.listAvailableKeyHouses()) {
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

        for (BuildingInterface starHouse : this.market.listAvailableStarHouses()) {
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

        for (BuildingInterface marketOutpost : this.market.listAvailableOutposts()) {
            BuildingComponent buildingComponent = new BuildingComponent();
            buildingComponent.setModel(marketOutpost);
            buildingComponent.load();

            this.outposts.getChildren().add(buildingComponent);
        }
    }
}