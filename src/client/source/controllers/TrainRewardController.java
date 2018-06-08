package client.source.controllers;

import client.source.Client;
import client.source.components.villager_to_train.VillagerComponent;
import client.source.observers.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import server.sources.actions.EndTurnAction;
import server.sources.interfaces.PlayerInterface;

import java.rmi.RemoteException;

public class TrainRewardController implements ControllerInterface, Observable {

    @FXML private Parent root;

    @FXML private FlowPane rewardComponent;

    @FXML private Button endTurnButton;

    private Client client;
    private VillagerComponent villager;

    @Override
    public Parent show() throws RemoteException {
        this.rewardComponent.getChildren().add(villager);
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

    public void setTrainReward(VillagerComponent villager) {
        this.villager = villager;
    }

    public void onClickEndTurn() throws RemoteException {
        try {
            client.getGameClient().getPlayer().doAction(new EndTurnAction());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}