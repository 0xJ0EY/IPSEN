package client.source.components.building;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import server.sources.models.buildings.Building;
import server.sources.models.buildings.House;
import server.sources.models.buildings.KeyHouse;
import server.sources.models.buildings.StarHouse;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Created by robin on 3-6-2018.
 */
public class KeyhouseComponent extends VBox {

    private static Client client;
    private Building building;

    @FXML
    VBox background;

    @FXML
    Rectangle building_image;

    @FXML
    Label info_label;


    public KeyhouseComponent(KeyHouse keyHouse){

        this.building = keyHouse;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/keyhouse.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            info_label.setText(this.building.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void buildKeyhouse() {
        // This is for displaying a message to warn user for not having enough coins to build a building.
        Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have enough coins to build a keyhouse.", ButtonType.OK);

        try {
            if (!this.building.canBuy(client.getGameClient().getPlayer())){
                alert.show();
            }
            else{
                client.getGameClient().getPlayer().getPlayerBoard().addHouse((KeyHouse) this.building);
                System.out.println("You've build a starhouse.");
                System.out.print(Arrays.toString(client.getGameClient().getPlayer().getPlayerBoard().getHouses().toArray()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setClient(Client c) { client = c; }
}
