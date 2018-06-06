package client.source.components.building;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import server.sources.models.buildings.Building;
import server.sources.models.buildings.StarHouse;

import java.io.IOException;

/**
 * Created by robin on 3-6-2018.
 */
public class StarhouseComponent extends VBox {

    private Building building;

    @FXML
    VBox background;

    @FXML
    Rectangle building_image;

    @FXML
    Label info_label;


    public StarhouseComponent(StarHouse house){

        this.building = house;

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
    private void buyStarHouse() {
        // TODO: Show indicator
        System.out.println("You've build a starhouse. Hello World");
    }

}
