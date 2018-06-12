package client.source.controllers;

import client.source.Client;
import client.source.components.villager.VillagerComponent;
import client.source.components.villager_to_train.TrainerVillagerComponent;
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
 * A class that acts as an intermediary between train view and models.
 * Created by robin on 7-6-2018.
 */
public class TrainController implements ControllerInterface {

    private Client client;
    private MarketInterface market;
    @FXML private Parent root;

    @FXML private FlowPane villagerContainer;

    @FXML private Button train;

    private VillagerInterface[] availableVillagers;

    private ArrayList<TrainerVillagerComponent> villagerComponents;
    private PlayerBoardInterface playerboard;

    /**
     * Loads available villagers to be recruited and trained
     * @return available villager units
     * @author Robin Silvério
     */
    @Override
    public Parent show() {

        try {
            this.retrieveVillagers();

            this.updateVillagersView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return this.root;
    }

    /**
     * For getting all villagers
     * @throws RemoteException
     * @author Robin Silvério
     */
    public void retrieveVillagers() throws RemoteException {
        this.availableVillagers = market.listAvailableVillagers();
    }

    /**
     * For updating all villagers to its containers.
     * @author Robin Silvério and Richard kerkvliet (for correcting code)
     */
    private void updateVillagersView() {
        this.villagerComponents = new ArrayList<TrainerVillagerComponent>();
        this.villagerContainer.getChildren().clear();

        for (int i = 0; i < this.availableVillagers.length; i++) {
            TrainerVillagerComponent villagerComponent = new TrainerVillagerComponent();
            villagerComponent.setModel(this.availableVillagers[i]);
            villagerComponent.load();

            villagerComponent.setOnMouseClicked( e->{
                villagerComponent.onClickSelect(villagerComponents);
                this.train.setDisable(false);
            });
            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    /**
     * Setting a client in trainview
     * @param client
     * @throws RemoteException
     * @author Robin Silvério and Richard Kerkvliet (for correcting code)
     */
    public void setClient(Client client) throws RemoteException {
        this.client = client;

        this.playerboard = client.getGameClient().getPlayer().getPlayerBoard();

        this.market = client.getGameClient().getServer().getGameController().getMarket();

    }

    /**
     * Allowes user to train chosen villager
     * @throws RemoteException
     * @author Richard Kerkvliet
     */
    public void onClickTrain() throws RemoteException {
        System.out.println("buying");

        for(int i=0; i < villagerComponents.size(); i++){
            TrainerVillagerComponent villager = villagerComponents.get(i);
            if(villager.isSelected()){
                market.buyRemoteVillager(client.getGameClient(), villager.getVillager());
                this.availableVillagers[i] = null;
                client.showTrainReward(villager);
            }
        }
    }
}
