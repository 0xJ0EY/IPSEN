package client.source.components.building;

import client.source.controllers.BuildController;
import client.source.controllers.SelectableControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.BuildingInterface;
import server.sources.interfaces.BuildingMarketInterface;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class that allows player to select multiple buildings in certain actions that a user chooses to perform.
 * Created by Joey de Ruiter
 */
public abstract class SelectableBuildingComponent extends BuildingComponent {

    private boolean selected = false;
    /**
     * Loads all multiple selecting buildingcomponents.
     * @author Joey de Ruiter
     */
    protected SelectableControllerInterface controller;

    public SelectableBuildingComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/selectable_building.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public abstract void onClickCard();

    /**
     * For selecting building.
     * @author Joey de Ruiter
     */
    public void deselect() {
        this.selected = false;
        this.update();
    }

    /**
     * For deselecting building.
     * @author Joey de Ruiter
     */
    public void select() {
        this.selected = true;
        this.update();
    }

    /**
     * For checking if a buildingcomponent has been selected.
     * @return boolean state (TRUE OR FALSE)
     * @author Joey de Ruiter
     */
    public boolean isSelected() {
        return this.selected;
    }

    public void toggleSelected() {
        this.selected = !this.selected;
        this.update();
    }

    /**
     * Updates the selection.
     * @author Joey de Ruiter
     */
    protected void update() {
        if (this.selected) {
            this.showIndicator();
        } else {
            this.hideIndicator();
        }
    }

    /**
     * Shows indicator when player chooses a building.
     * @author Joey de Ruiter
     */
    protected void showIndicator(){
        try {
            this.background.setStyle(
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);" +
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
     * Hides an indicator when player deselects a building.
     * @author Joey de Ruiter
     */
    protected void hideIndicator(){
        try {
            this.background.setStyle(
                "-fx-effect: dropshadow(three-pass-box, white, 00, 0, 0, 0);" +
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
     * For setting a controller.
     * @param controller SelectableControllerInterface
     * @author Joey de Ruiter
     */
    @FXML
    public void setController(SelectableControllerInterface controller) {
        this.controller = controller;
    }
}
