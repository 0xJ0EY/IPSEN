package client.source.controllers;

import client.source.Client;
import client.source.components.villager.VillagerComponent;
import client.source.components.villager_to_train.TrainerVillagerComponent;
import client.source.observers.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import server.sources.interfaces.MarketInterface;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A class that acts as an intermediary between train view and models.
 * Created by robin on 7-6-2018.
 */
public class TrainController implements ControllerInterface {

    private Client client;
    private MarketInterface market;
    @FXML private Parent root;

    @FXML private GridPane villagerContainer;

    @FXML private Button cancelButton;

    @FXML private Button buyButton;

    private VillagerInterface[] availableVillagers;

    private ArrayList<TrainerVillagerComponent> villagerComponents;
    private PlayerBoardInterface playerboard;
    private PlayerInterface target;

    /**
     * Loads available villagers to be recruited and trained
     * @return available villager units
     * @author Robin Silverio
     */
    @Override
    public Parent show() {

        try {
            this.updateButtons();

            this.retrieveVillagers();

            this.updateVillagersView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return this.root;
    }

    /**
     * For getting all villagers
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    public void retrieveVillagers() throws RemoteException {
        this.availableVillagers = market.listAvailableVillagers();
    }

    /**
     * For updating all villagers to its containers.
     * @author Robin Silverio and Richard kerkvliet (for correcting code)
     */
    private void updateVillagersView() {
        this.villagerComponents = new ArrayList<TrainerVillagerComponent>();
        this.villagerContainer.getChildren().clear();

        for (int i = 0; i < this.availableVillagers.length; i++) {
            if (this.availableVillagers[i] == null) continue;

            TrainerVillagerComponent villagerComponent = new TrainerVillagerComponent();
            villagerComponent.setModel(this.availableVillagers[i]);
            villagerComponent.load();

            villagerComponent.setOnMouseClicked( e->{
                villagerComponent.onClickSelect(villagerComponents);
                this.buyButton.setDisable(false);
            });
            this.villagerComponents.add(villagerComponent);


            GridPane.setColumnIndex(villagerComponent, i);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    /**
     * Setting a client in trainview
     * @param client
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio and Richard Kerkvliet (for correcting code)
     */
    public void setClient(Client client) throws RemoteException {
        this.client = client;

        this.playerboard = client.getGameClient().getPlayer().getPlayerBoard();

        this.market = client.getGameClient().getServer().getGameController().getMarket();

    }

    /**
     * Allowes user to train chosen villager
     * @throws RemoteException java.rmi.RemoteException
     * @author Richard Kerkvliet
     */
    public void onClickTrain() throws RemoteException {
        System.out.println("buying");
        for(int i=0; i < villagerComponents.size(); i++){
            TrainerVillagerComponent villager = villagerComponents.get(i);
            if(villager.isSelected()){
                playerboard.payCoin(villager.getPrice());
                market.buyRemoteVillager(client.getGameClient(), villager.getVillager());
                this.availableVillagers[i] = null;
                client.showTrainReward(villager);
            }
        }
    }

    public void onClickCancel() throws RemoteException{
        client.showMain();
    }

    public void updateButtons() {
        this.target = client.turnObserver.getState();
        try {
            boolean turn = !this.target.getGameClient().equals(client.getGameClient());
            this.cancelButton.setDisable(turn);
            this.buyButton.setDisable(turn);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
