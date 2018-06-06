package client.source.components.building;

import client.source.Client;
import client.source.controllers.BuildController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.models.Player;
import server.sources.models.buildings.Building;
import server.sources.models.buildings.House;
import server.sources.models.villagers.Villager;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 2-6-2018.
 */
public class HouseComponent extends VBox {

    private static Client client;
    private PlayerBoardControllerInterface playerBoard;
    private Building building;

    @FXML
    VBox background;

    @FXML
    Rectangle building_image;

    @FXML
    Label info_label;


    public HouseComponent(House house){
        this.building = house;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/building/house.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            info_label.setText(this.building.toString());
//            playerBoard = client.getGameClient().getPlayer().getPlayerBoard();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * For handling clickEvents when user buys a house.
     * @author Robin Silvério
     */
    @FXML
    private void buyHouse() {
        // TODO: Show indicator
        try {
            if (!this.building.canBuy(client.getGameClient().getPlayer())){
                System.out.println("You don't have enough coins.");
            }
            else{
                System.out.println("You've build a house. Hello World");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void setClient(Client c){
        client = c;
    }

}
