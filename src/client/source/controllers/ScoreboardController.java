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

    private ObservableList<PlayerInterface> masterData = FXCollections.observableArrayList();

    @Override
    public Parent show() {

        populateScoreBoard();
        return root;
    }

    public void populateScoreBoard() {
        // TODO: Populate all data in a tableview.


        ArrayList<PlayerInterface> players = client.clientObserver.getState();

        for(PlayerInterface player : players){
            masterData.add(player);
        }

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

        total_points_data.setCellValueFactory(c -> {

            int totalPoints = 0;

            SimpleStringProperty pt = null;

            try {
                totalPoints = c.getValue().getAmountBuildings() + c.getValue().getReputation() + c.getValue().getAmountOfCardBonusses();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            pt = new SimpleStringProperty(Integer.toString(totalPoints));
            return pt;
        });


        // 3. Wrap the FilteredList in a SortedList.
        SortedList<PlayerInterface> sortedData = new SortedList<>(masterData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(score_table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        score_table.setItems(sortedData);
    }

    public static void setClient(Client c) { client = c; }
}
