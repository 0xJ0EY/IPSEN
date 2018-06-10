package client.source.components.building;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import server.sources.actions.EndTurnAction;
import server.sources.models.buildings.Building;
import server.sources.models.buildings.House;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Created by robin on 2-6-2018.
 */
public class HouseComponent extends VBox {

    private static Client client;
    private Building building;

    @FXML
    VBox background;

    @FXML
    Rectangle building_image;

    @FXML
    Label info_label;


    public HouseComponent(House house){
        this.building = house;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/house.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            info_label.setText(this.building.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * For handling clickEvents when user builds a house with selected villager.
     * @author Robin Silvério
     */
    @FXML
    private void buildHouse() {
        // This is for displaying a message to warn user for not having enough coins to build a building.
        Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have enough coins to build a house.", ButtonType.OK);

        try {
            if (!this.building.canBuy(client.getGameClient().getPlayer())){
                alert.show();
            }
            else{
                client.getGameClient().getPlayer().getPlayerBoard().addHouse((House) this.building);
                System.out.println("You've build a house.");
                System.out.print(Arrays.toString(client.getGameClient().getPlayer().getPlayerBoard().getHouses().toArray()));
                client.getGameClient().getPlayer().doAction(new EndTurnAction());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setClient(Client c){
        client = c;
    }

}
