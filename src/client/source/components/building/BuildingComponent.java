package client.source.components.building;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.BuildingInterface;
import server.sources.models.perks.Harvestable;
import server.sources.models.perks.Perk;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class that loads a building components ready to be set on containers in aboveview, marketview, etc.
 * Created by Joey de Ruiter and before Robin (redundant code)
 */
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

    /**
     * Loads buildcomponent and is set on container.
     * @author Richard Kerkvliet and Joey de Ruiter (for refactoring)
     */
    public void load() {
        try {
            for (Perk perk : this.building.listPerks()) {
                PerkComponent perkComponent = null;

                if (perk instanceof Harvestable) {
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

    /**
     * Setting a model.
     * @param building BuildingInterface
     * @author Joey de Ruiter
     */
    public void setModel(BuildingInterface building) {
        this.building = building;
    }

    /**
     * Gets the model.
     * @return building
     * @author Joey de Ruiter
     */
    public BuildingInterface getModel() {
        return this.building;
    }
}
