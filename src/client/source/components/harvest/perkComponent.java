package client.source.components.harvest;

import client.source.components.villager.VillagerComponent;
import client.source.controllers.HarvestController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.buildings.Building;
import server.sources.models.perks.Harvastable;

import java.io.IOException;

public class PerkComponent extends AnchorPane {
    private  HarvestController controller;
    private Building building;
    private boolean selected = false;

    @FXML AnchorPane backgound;

    @FXML AnchorPane goodPerk;

    public PerkComponent(Building building, HarvestController harvestController){
        this.building = building;

        this.controller = harvestController;

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/villager/perk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.goodPerk.getChildren().setAll(building.getGoodComponent());

        this.backgound.setStyle("-fx-background-color: grey");
    }

    @FXML
    public void selectedGood(){
        controller.onlyOneTrue(this);
    }

    public void setFalse(){
        selected = false;
        backgound.setStyle("-fx-effect: dropshadow(three-pass-box, black, 0, 0, 0, 0);");
    }

    public void setTrue(){
        selected = true;
        backgound.setStyle("-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");

    }

    public boolean isSelected(){
        return selected;
    }



}
