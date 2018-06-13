package client.source.controllers;

import client.source.Client;
import client.source.components.villager_to_train.TrainerVillagerComponent;
import client.source.observers.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import server.sources.actions.EndTurnAction;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class TrainRewardController implements ControllerInterface, Observable {

    @FXML private Parent root;

    @FXML private FlowPane rewardComponent;

    @FXML private Button endTurnButton;

    private Client client;
    private TrainerVillagerComponent villager;

    @Override
    public Parent show() throws RemoteException {
        this.rewardComponent.getChildren().add(villager);
        this.updateObserver();
        return this.root;
    }

    /**
     * Observes any updates.
     * @author Richard Kerkvliet
     */
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

    /**
     * Gives a selected trainer villager a reward for recruiting new villager.
     * @param villager a selected villager before performing a train action
     * @author Richard Kerkvliet
     */
    public void setTrainReward(TrainerVillagerComponent villager) {
        this.villager = villager;
    }

    /**
     * Ends turn after user clicks on end turn button
     * @throws RemoteException java.rmi.RemoteException
     * @author Richard Kerkvliet
     */
    public void onClickEndTurn() throws RemoteException {
        try {
            EndTurnAction endTurnAction = new EndTurnAction();
            client.getGameClient().getPlayer().doAction(endTurnAction);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
