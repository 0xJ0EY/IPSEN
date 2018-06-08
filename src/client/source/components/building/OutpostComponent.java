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
import server.sources.models.buildings.Outpost;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Created by robin on 3-6-2018.
 */
public class OutpostComponent extends VBox {

    private static Client client;
    private Building building;

    @FXML
    VBox background;

    @FXML
    Rectangle building_image;

    @FXML
    Label info_label;


    public OutpostComponent(Outpost outpost){

        this.building = outpost;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/outpost.fxml"));

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
     * For handling clickEvents when user builds an outpost with a selected villager.
     * @author Robin Silvério
     */
    @FXML
    private void buildOutpost() {
        // This is for displaying a message to warn user for not having enough coins to build a building.
        Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have enough coins to build an outpost.", ButtonType.OK);

        try {
            if (!this.building.canBuy(client.getGameClient().getPlayer())){
                alert.show();
            }
            else{
                client.getGameClient().getPlayer().getPlayerBoard().addOutpost((Outpost) this.building);
                System.out.println("You've build an outpost.");
                System.out.print(Arrays.toString(client.getGameClient().getPlayer().getPlayerBoard().getOutposts().toArray()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setClient(Client c){
        client = c;
    }
}
