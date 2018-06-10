package client.source.components.building;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.perks.Perk;

import java.io.IOException;

public class PerkComponent extends AnchorPane {

    @FXML private AnchorPane background;

    private BuildingComponent buildingComponent;

    private Perk perk;

    public PerkComponent(){
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/perk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        this.background.setStyle(
            "-fx-background-image: url('/client/resources/img/perks/" + this.perk.getBackground() + " ');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 50 50;"
        );
    }

    public void setBuildingComponent(BuildingComponent buildingComponent) {
        this.buildingComponent = buildingComponent;
    }

    public void setModel(Perk perk) {
        this.perk = perk;
    }

    public Perk getPerk() {
        return this.perk;
    }
}
