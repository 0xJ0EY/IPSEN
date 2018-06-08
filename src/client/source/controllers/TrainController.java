package client.source.controllers;

import client.source.Client;
import client.source.components.villager_to_train.VillagerComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.MarketInterface;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

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

    @FXML private Button train;

    private VillagerInterface[] availableVillagers;

    private ArrayList<VillagerComponent> villagerComponents;
    private PlayerBoardInterface playerboard;

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
            villagerComponent.setOnMouseClicked( e->{
                villagerComponent.onClickSelect(villagerComponents);
                this.train.setDisable(false);
            });
            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    @Override
    public void updateObserver() {}

    public void setClient(Client client) throws RemoteException {
        this.client = client;

        this.playerboard = client.getGameClient().getPlayer().getPlayerBoard();

        this.market = client.getGameClient().getServer().getGameController().getMarket();

        this.client.clientObserver.attach(this);
    }

    public void onClickTrain() throws RemoteException {
        System.out.println("buying");

        for(int i=0; i < villagerComponents.size(); i++){
            VillagerComponent villager = villagerComponents.get(i);
            if(villager.isSelected()){
                market.buyRemoteVillager(client.getGameClient(), villager.getVillager());
                this.availableVillagers[i] = null;
                client.showTrainReward(villager);
            }
        }
    }
}
