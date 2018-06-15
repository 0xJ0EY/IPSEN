package client.source.components.harvest;

import client.source.components.building.BuildingComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.perks.Perk;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class that creates perk component
 * Created by Richard Kerkvliet
 */
public class PerkComponent extends AnchorPane {

    @FXML private AnchorPane background;

    private BuildingComponent buildingComponent;

    private Perk perk;

    public PerkComponent(BuildingComponent buildingComponent){
        this.buildingComponent = buildingComponent;

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/perk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * For loading perks.
     * @author Richard Kerkvliet
     */
    public void load() throws RemoteException {
        this.background.setStyle(
            "-fx-background-image: url('/client/resources/img/perks/" + this.perk.getBackground() + " ');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 50 50;"
        );
    }

    /**
     * For setting a model.
     * @param perk Perk
     * @author Richard Kerkvliet
     */
    public void setModel(Perk perk) {
        this.perk = perk;
    }

    /**
     * For getting perks.
     * @return perk
     * @author Richard Kerkvliet
     */
    public Perk getPerk() {
        return this.perk;
    }
}
