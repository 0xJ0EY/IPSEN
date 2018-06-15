package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A class that acts as an intermediator between menuview and models
 */
public class MenuController implements Observable {

    private enum Tabs { ABOVE, BELOW, MARKET, TURN, SETTINGS, RULES }

    private Client client;

    private TabPane tabContainer;

    @FXML private Parent root;

    @FXML private Button turnButton;
    @FXML private Button settingsButton;
    @FXML private Button marketButton;

    @FXML private ListView playerList;

    private ObservableList<String> playerItems = FXCollections.observableArrayList();

    /**
     * Loads all players registrated in a listview after they have entered the game environment.
     * @author Joey de Ruiter
     */
    @FXML public void initialize() {
        this.playerList.setItems(playerItems);
    }

    /**
     * These methods are used for opening a selected tab.
     */
    @FXML private void onClickAbove() {
        tabContainer.getSelectionModel().select(Tabs.ABOVE.ordinal());
    }

    @FXML private void onClickBelow() {
        tabContainer.getSelectionModel().select(Tabs.BELOW.ordinal());
    }

    @FXML private void onMarketClick() {
        tabContainer.getSelectionModel().select(Tabs.MARKET.ordinal());
    }

    @FXML private void onTurnClick() {
        tabContainer.getSelectionModel().select(Tabs.TURN.ordinal());
    }

    @FXML private void onClickSettings() {
        tabContainer.getSelectionModel().select(Tabs.SETTINGS.ordinal());
    }

    @FXML public void onRulesClick() {
        tabContainer.getSelectionModel().select(Tabs.RULES.ordinal());
    }

    @FXML public void select() {
        root.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            switch (keyCode){
                case DIGIT1:
                    this.onClickAbove();
                    break;
                case DIGIT2:
                    this.onClickBelow();
                    break;
                case DIGIT3:
                    this.onMarketClick();
                    break;
                case DIGIT4:
                    if(!this.turnButton.isDisabled()) {
                        this.onTurnClick();
                    }
                    break;
            }
        });
    }

    /**
     * This is for enabling and disabling a startbutton
     */
    public void enableTurnButton() {
        this.turnButton.setDisable(false);
    }

    public void disableTurnButton() {
        this.turnButton.setDisable(true);
    }

    /**
     * This is for assigning a tab container.
     * @param tabContainer
     */
    public void assignTabContainer(TabPane tabContainer) {
        this.tabContainer = tabContainer;
    }

    public void registerClient(Client client) {
        this.client = client;
        this.client.clientObserver.attach(this);
        this.client.turnObserver.attach(this);
    }

    /**
     * Observes any updates in the menu view
     * @author Joey de Ruiter
     */
    @Override
    public void updateObserver() {

        // Update player list
        ArrayList<PlayerInterface> players = this.client.clientObserver.getState();
        this.playerItems.clear();

        for (PlayerInterface player : players) {
            try {
                playerItems.add(player.getUsername());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        try {
            this.settingsButton.setDisable(!this.client.getGameClient().isOwner());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Show or hide turn buttons
        PlayerInterface target = this.client.turnObserver.getState();

        // No target, so its not even worth going here
        if (target == null) return;

        try {
            boolean turn = target.getGameClient().equals(this.client.getGameClient());
            this.turnButton.setDisable(!turn);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
