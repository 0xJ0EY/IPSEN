package client.source.controllers;

import client.source.Client;
import client.source.components.reward.CiderRewardComponent;
import client.source.components.reward.CoinRewardComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import server.sources.actions.EndTurnAction;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LaborRewardController implements ControllerInterface, Observable {

    @FXML private Parent root;

    @FXML private FlowPane rewardComponent;

    @FXML private Button endTurnButton;

    private Client client;

    private ArrayList<VillagerInterface> villagers;
    private boolean ciderReward;

    @Override
    public Parent show() throws RemoteException {
        CoinRewardComponent coinRewardComponent = new CoinRewardComponent(villagers.size());
        coinRewardComponent.load();
        this.rewardComponent.getChildren().add(coinRewardComponent);
        if (this.ciderReward) {
            CiderRewardComponent ciderRewardComponent = new CiderRewardComponent();
            ciderRewardComponent.load();
            this.rewardComponent.getChildren().add(ciderRewardComponent);
        }
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

    public void setVillagers(ArrayList<VillagerInterface> villagers){
        this.villagers = villagers;
    }
    public void setCider(boolean ciderReward){
        this.ciderReward = ciderReward;
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

    @FXML public void keys() {
        root.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            switch (keyCode) {
                case ENTER:
                    try {
                        this.onClickEndTurn();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    break;

            }
        });
    }
}
