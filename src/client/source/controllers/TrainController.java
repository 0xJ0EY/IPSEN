package client.source.controllers;

import client.source.Client;
import client.source.components.villager_to_train.VillagerComponent;
import client.source.observers.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.MarketInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.Market;
import server.sources.models.villagers.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 7-6-2018.
 */
public class TrainController implements ControllerInterface, Observable {

    private Client client;
    private MarketInterface market;
    @FXML private Parent root;

    @FXML private FlowPane villagerContainer;

    private VillagerInterface[] availableVillagers;

    private ArrayList<VillagerComponent> villagerComponents;

    @Override
    public Parent show() {

        try {
            this.retrieveVillagers();

            this.updateVillagersView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        updateObserver();
        return this.root;
    }

    /**
     * For getting all villagers
     * @throws RemoteException
     * @author: Robin Silvério
     */
    public void retrieveVillagers() throws RemoteException {
        this.availableVillagers = market.listAvailableVillagers();
    }

    /**
     * For updating all villagers to its containers.
     * @author: Robin Silvério
     */
    private void updateVillagersView() {
        this.villagerComponents = new ArrayList<VillagerComponent>();
        this.villagerContainer.getChildren().clear();

        for (int i = 0; i < this.availableVillagers.length; i++) {
            VillagerComponent villagerComponent = new VillagerComponent(this.availableVillagers[i]);
            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    @Override
    public void updateObserver() {}

    public void setClient(Client client) throws RemoteException {
        this.client = client;

        this.market = client.getGameClient().getServer().getGameController().getMarket();

        this.client.clientObserver.attach(this);
    }

    public void onClickTrain() {
        System.out.println("buying");
    }
}
