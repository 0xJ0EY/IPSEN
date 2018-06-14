package client.source.controllers;

import client.source.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.sources.interfaces.PlayerInterface;
import server.sources.models.Player;
import server.sources.models.perks.Perk;
import server.sources.models.perks.VillagePointsPerk;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 8-6-2018.
 */
public class ScoreboardController implements ControllerInterface {

    private Client client;

    @FXML private Parent root;
    @FXML private TableView<PlayerInterface> scoreTable;

    @FXML private TableColumn<PlayerInterface, String> playerData;
    @FXML private TableColumn<PlayerInterface, String> advancementTrackData;
    @FXML private TableColumn<PlayerInterface, String> reputationData;
    @FXML private TableColumn<PlayerInterface, String> buildingsData;
    @FXML private TableColumn<PlayerInterface, String> cardBonusData;
    @FXML private TableColumn<PlayerInterface, String> totalPointsData;

    // With this observablelist, we can retrieve all scores from each player and populate them in a tableview.
    private ObservableList<PlayerInterface> masterData = FXCollections.observableArrayList();

    @Override
    public Parent show() {

        populateScoreBoard();
        return root;
    }

    /**
     * This is for populating a scoreboard with all the scores.
     * @author Robin Silverio
     */
    public void populateScoreBoard() {

        ArrayList<PlayerInterface> players = client.clientObserver.getState();

        for(PlayerInterface player : players){
            masterData.add(player);
        }

        playerData.setCellValueFactory(c -> {
            SimpleStringProperty pt = null;
            try {
                pt = new SimpleStringProperty(c.getValue().getUsername());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return pt;
        });

        advancementTrackData.setCellValueFactory(c -> {
            int points = 0;

            try {
                points = c.getValue().getPlayerBoard().getAdvancementTracker().calculatePoints();

            } catch (RemoteException e) {
                e.printStackTrace();
            }

            return new SimpleStringProperty(Integer.toString(points));
        });


        reputationData.setCellValueFactory(c -> {
            SimpleStringProperty pt = null;
            try {
                pt = new SimpleStringProperty(Integer.toString(c.getValue().getReputation()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return pt;
        });

        buildingsData.setCellValueFactory(c -> {
            SimpleStringProperty pt = null;

            try {
                pt = new SimpleStringProperty(Integer.toString(c.getValue().getAmountBuildings()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            return pt;
        });

        cardBonusData.setCellValueFactory(c -> {
            String pointsFromPlayer = Integer.toString(this.getVillagerPointsFromPlayer());
            return new SimpleStringProperty(pointsFromPlayer);
        });

        totalPointsData.setCellValueFactory(c -> {

            int totalPoints = 0;

            try {
                totalPoints += c.getValue().getPlayerBoard().getAdvancementTracker().calculatePoints();
                totalPoints += c.getValue().getAmountBuildings();
                totalPoints += c.getValue().getReputation();
                totalPoints += this.getVillagerPointsFromPlayer();

            } catch (RemoteException e) {
                e.printStackTrace();
            }

            return new SimpleStringProperty(Integer.toString(totalPoints));
        });


        // Wrap the FilteredList in a SortedList.
        SortedList<PlayerInterface> sortedData = new SortedList<>(masterData);

        // Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(scoreTable.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        scoreTable.setItems(sortedData);
    }

    /**
     * This is for setting a client. How can we retrieve all clients if each of them is not set.
     * @param client
     * @author Robin Silverio
     */
    public void setClient(Client client) {
        this.client = client;
    }

    private int getVillagerPointsFromPlayer() {

        int points = 0;

        try {
            // Get playerboard
            ArrayList<Perk> perks = this.client.getGameClient().getPlayer().getPlayerBoard().getBuildingsPerks();

            for (Perk perk : perks) {
                if (perk instanceof VillagePointsPerk) {
                    points += ((VillagePointsPerk) perk).getValue();
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return points;
    }
}
