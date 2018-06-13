package client.source.components.villager;

import client.source.controllers.VillagerSelectionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * This class allows to create selectable villager components
 * @author Richard Kerkvliet
 */
public abstract class SelectableVillagerComponent extends VillagerComponent {

    protected VillagerSelectionController controller;

    protected boolean selectable = true;
    protected boolean selected;

    public SelectableVillagerComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/selectable_villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public abstract void onClickSelect();

    /**
     * For deselecting villager.
     * @author Richard Kerkvliet
     */
    public void deselect() {
        this.selected = false;
        this.update();
    }

    /**
     * For selecting villager.
     * @author Richard Kerkvliet
     */
    public void select() {
        if (!this.isSelectable()) {
            this.controller.showMessage("You already have selected the maximum amount of villagers.");
            return;
        }

        this.selected = true;
        this.update();
    }

    /**
     * For checking if villager has been selected.
     * @return boolean state (TRUE or FALSE)
     * @author Richard Kerkvliet
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * Warns the player that the villager has been selected by toggling selected villagers.
     * @author Richard Kerkvliet
     */
    public void toggleSelected() {
        if (this.isSelected()) {
            this.deselect();
        }
        else {
            this.select();
        }
    }

    protected void update() {
        this.controller.update();

        if (this.selected) {
            this.showIndicator();
        } else {
            this.hideIndicator();
        }
    }

    protected void showIndicator(){
        try {
            this.background.setStyle(
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);" +
                "-fx-background-image: url('/client/resources/img/villager_backgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 115 205"
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    protected void hideIndicator(){
        try {
            this.background.setStyle(
                "-fx-effect: dropshadow(three-pass-box, white, 00, 0, 0, 0);" +
                "-fx-background-image: url('/client/resources/img/villager_backgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 110 200"
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * For setting a controller
     * @param controller VillagerSelectionController
     * @author Joey de Ruiter
     */
    public void setController(VillagerSelectionController controller) {
        this.controller = controller;
    }

    private boolean isSelectable() {
        return this.selectable;
    }

    public void enableSelection() {
        this.selectable = true;

    }

    public void disableSelection() {
        this.selectable = false;
    }
}
