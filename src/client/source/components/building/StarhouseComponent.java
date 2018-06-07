package client.source.components.building;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import server.sources.models.buildings.Building;
import server.sources.models.buildings.House;
import server.sources.models.buildings.StarHouse;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Created by robin on 3-6-2018.
 */
public class StarhouseComponent extends VBox {

    private static Client client;
    private Building building;

    @FXML
    VBox background;

    @FXML
    Rectangle building_image;

    @FXML
    Label info_label;


    public StarhouseComponent(StarHouse starHouse){

        this.building = starHouse;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/building/starhouse.fxml"));

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
    private void buildStarHouse() {
        // TODO: Show indicator
        try {
            if (!this.building.canBuy(client.getGameClient().getPlayer())){
                System.out.println("You don't have enough coins.");
            }
            else{
                client.getGameClient().getPlayer().getPlayerBoard().addHouse((StarHouse) this.building);
                System.out.println("You've build a starhouse.");
                System.out.print(Arrays.toString(client.getGameClient().getPlayer().getPlayerBoard().getHouses().toArray()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setClient(Client c){ client = c; }

}
