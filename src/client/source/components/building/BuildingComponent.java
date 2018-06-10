package client.source.components.building;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.BuildingInterface;
import server.sources.models.perks.Harvastable;
import server.sources.models.perks.Perk;

import java.io.IOException;
import java.rmi.RemoteException;

public class BuildingComponent extends AnchorPane {

    protected BuildingInterface building;

    @FXML protected AnchorPane background;

    @FXML protected FlowPane perks;

    public BuildingComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/building.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            for (Perk perk : this.building.listPerks()) {
                PerkComponent perkComponent = null;

                if (perk instanceof Harvastable) {
                    perkComponent = new RefreshablePerkComponent();
                } else {
                    perkComponent = new PerkComponent();
                }

                perkComponent.setBuildingComponent(this);
                perkComponent.setModel(perk);
                perkComponent.load();

                this.perks.getChildren().add(perkComponent);
            }

            this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/buildings/" + this.building.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 120 200"
            );

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setModel(BuildingInterface building) {
        this.building = building;
    }

    public BuildingInterface getModel() {
        return this.building;
    }
}
