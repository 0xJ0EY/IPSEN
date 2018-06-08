package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML private TableView score_table;

    @FXML private TableColumn player_data;
    @FXML private TableColumn advancement_track_data;
    @FXML private TableColumn buildings_data;
    @FXML private TableColumn card_bonus_data;
    @FXML private TableColumn total_points_data;

    @Override
    public Parent show() {

        populateScoreBoard();
        return root;
    }

    public void populateScoreBoard(){
        // TODO: Populate all data in a tableview.
        ArrayList<PlayerInterface> players = client.clientObserver.getState();

        for (PlayerInterface player : players){
            try {
                System.out.println(player.getUsername() + " " + player.getAmountBuildings() + " " + player.getAmountOfCardBonusses());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setClient(Client c) { client = c; }
}
