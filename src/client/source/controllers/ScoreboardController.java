package client.source.controllers;

import client.source.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import server.sources.interfaces.PlayerInterface;
import server.sources.models.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 8-6-2018.
 */
public class ScoreboardController implements ControllerInterface {

    private static Client client;

    @FXML private Parent root;
    @FXML private TableView<PlayerInterface> score_table;

    @FXML private TableColumn<PlayerInterface, String> player_data;
    @FXML private TableColumn<PlayerInterface, String> advancement_track_data;
    @FXML private TableColumn<PlayerInterface, String> reputation_data;
    @FXML private TableColumn<PlayerInterface, String> buildings_data;
    @FXML private TableColumn<PlayerInterface, String> card_bonus_data;
    @FXML private TableColumn<PlayerInterface, String> total_points_data;

    @Override
    public Parent show() {

        populateScoreBoard();
        return root;
    }

    public void populateScoreBoard() {
        // TODO: Populate all data in a tableview.

        int totalPoints = 0;

        ArrayList<PlayerInterface> players = client.clientObserver.getState();

        for (PlayerInterface player : players){

            player_data.setCellValueFactory(c -> {
                SimpleStringProperty pt = null;
                try {
                    pt = new SimpleStringProperty(c.getValue().getUsername());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return pt;
            });

//            advancement_track_data.setCellValueFactory(c -> {
//                SimpleStringProperty pt = null;
//                try {
//                    pt = new SimpleStringProperty(c.getValue().getUsername());
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//                return pt;
//            });
            reputation_data.setCellValueFactory(c -> {
                SimpleStringProperty pt = null;
                try {
                    pt = new SimpleStringProperty(Integer.toString(c.getValue().getReputation()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return pt;
            });
            buildings_data.setCellValueFactory(c -> {
                SimpleStringProperty pt = null;

                try {
                    pt = new SimpleStringProperty(Integer.toString(c.getValue().getAmountBuildings()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                return pt;
            });
            card_bonus_data.setCellValueFactory(c -> {
                SimpleStringProperty pt = null;

                try {
                    pt = new SimpleStringProperty(Integer.toString(c.getValue().getAmountOfCardBonusses()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                return pt;
            });

            try {
                totalPoints = player.getAmountBuildings() + player.getReputation() + player.getAmountOfCardBonusses();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            int finalTotalPoints = totalPoints;
            total_points_data.setCellValueFactory(c -> {
                SimpleStringProperty pt = null;
                pt = new SimpleStringProperty(Integer.toString(finalTotalPoints));
                return pt;
            });
        }
        score_table.getSortOrder().add(total_points_data); // This is for sorting a table by total of points. Easily to decide the winner.
        score_table.getItems().addAll(players);
    }

    public static void setClient(Client c) { client = c; }
}
