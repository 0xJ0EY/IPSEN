package client.source.components.harvest;

import client.source.components.villager.VillagerComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.buildings.Building;

import java.io.IOException;

public class perkComponent extends AnchorPane {
    private Building building;

    @FXML AnchorPane backgound;

    @FXML AnchorPane perk;

    public perkComponent(Building building){
        this.building = building;

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/villager/harvest.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //this.perk.getChildren().setAll(building.getPerk());

        this.backgound.setStyle("-fx-background-color: grey");
    }

    @FXML public void selectedGood(){


    }



}
