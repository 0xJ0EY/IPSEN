package client.source.controllers;

import client.source.Client;
import client.source.components.reward.*;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import server.sources.actions.EndTurnAction;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.stories.Reward;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A class that acts as an intermediate between a rewardview and models.
 * Created by Richard Kerkvliet.
 */
public class RewardController implements ControllerInterface, Observable {

    @FXML private Parent root;

    @FXML private FlowPane rewardComponent;

    @FXML private Button endTurnButton;

    private Client client;
    private ArrayList<VillagerInterface> villagers;

    private ArrayList<Reward> rewards;

    public void setClient(Client client) {
        this.client = client;

        // Register the client as observer
        this.client.clientObserver.attach(this);
    }

    /**
     * Loads all rewards in rewardsview
     * @return a loaded reward.fxml
     * @throws RemoteException java.rmi.RemoteException
     */
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

    /**
     * Observes all updates in rewardview.
     * @author Richard Kerkvliet.
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

    /**
     * Setting rewards on rewardview
     * @param rewards
     * @author Richard Kerkvliet
     */
    public void setRewards(ArrayList<Reward> rewards){
        this.rewards = rewards;
    }

    public void setVillagers(ArrayList<VillagerInterface> villagers){
        this.villagers = villagers;
    }

    /**
     * Ends turn when player clicks on end turn button.
     * @throws RemoteException java.rmi.RemoteException
     * @author Richard Kerkvliet
     */
    public void onClickEndTurn() throws RemoteException {
        try {
            client.getGameClient().getPlayer().doAction(new EndTurnAction(this.villagers));
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
