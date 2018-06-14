package client.source.controllers;

import client.source.Client;
import client.source.components.building.BuildingComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import server.sources.actions.EndTurnAction;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.buildings.House;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class BuildRewardController implements ControllerInterface, Observable {

    @FXML private Parent root;

    @FXML private FlowPane rewardComponent;

    @FXML private Button endTurnButton;

    private Client client;
    private ArrayList<VillagerInterface> villagers;
    private BuildingComponent rewardBuilding;

    @Override
    public Parent show() throws RemoteException {
        //this.rewardComponent.getChildren().add(new HouseRewardComponent());
        this.updateObserver();
        return this.root;
    }

    @Override
    public void updateObserver() {
        PlayerInterface target = this.client.turnObserver.getState();
        try {
            boolean turn = this.client.getGameClient().equals(target.getGameClient());
            this.endTurnButton.setDisable(!turn);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setClient(Client client) {
        this.client = client;
        this.client.clientObserver.attach(this);
    }

    // TODO: 14/06/2018 use 
    public void setRewardBuilding(BuildingComponent rewardBuilding){
        this.rewardBuilding = rewardBuilding;
    }

    // TODO: 14/06/2018 use 
    public void setVillagers(ArrayList<VillagerInterface> villagers){
        this.villagers = villagers;
    }

    /**
     * Ends turn after user clicks on end turn button
     * @throws RemoteException java.rmi.RemoteException
     * @author Richard Kerkvliet
     */
    public void onClickEndTurn() throws RemoteException {
        try {
            EndTurnAction endTurnAction = new EndTurnAction(this.villagers);
            client.getGameClient().getPlayer().doAction(endTurnAction);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
