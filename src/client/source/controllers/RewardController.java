package client.source.controllers;

import client.source.Client;
import client.source.components.reward.*;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import server.sources.actions.EndTurnAction;
import server.sources.interfaces.PlayerInterface;
import server.sources.models.stories.Reward;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RewardController implements ControllerInterface, Observable {

    @FXML private Parent root;

    @FXML private FlowPane rewardComponent;

    @FXML private Button endTurnButton;

    private Client client;

    private ArrayList<Reward> rewards;

    public void setClient(Client client) {
        this.client = client;

        // Register the client as observer
        this.client.clientObserver.attach(this);
    }

    @Override
    public Parent show() throws RemoteException {

        for (Reward reward: rewards) {

            RewardComponent rewardComponent = rewardType(reward);
            rewardComponent.setModel(reward);
            rewardComponent.load();
            
            this.rewardComponent.getChildren().add(rewardComponent);
        }

        this.updateObserver();
        return this.root;
    }

    private RewardComponent rewardType(Reward reward) {
        return reward.getRewardComponent();
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

    public void setRewards(ArrayList<Reward> rewards){
        this.rewards = rewards;
    }

    public void onClickEndTurn() throws RemoteException {
        try {
            client.getGameClient().getPlayer().doAction(new EndTurnAction());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
