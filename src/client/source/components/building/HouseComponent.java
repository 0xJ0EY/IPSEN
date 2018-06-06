package client.source.components.building;

import client.source.controllers.BuildController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void buyHouse() {
        // TODO: Show indicator
        System.out.println("You've build a house. Hello World");
    }

}
