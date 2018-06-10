package client.source.components.building;

import client.source.controllers.BuildController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.BuildingInterface;
import server.sources.interfaces.BuildingMarketInterface;

import java.io.IOException;
import java.rmi.RemoteException;

public abstract class SelectableBuildingComponent extends BuildingComponent {

    private boolean selected = false;

    protected BuildController controller;

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

    public void deselect() {
        this.selected = false;
        this.update();
    }

    public void select() {
        this.selected = true;
        this.update();
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void toggleSelected() {
        this.selected = !this.selected;
        this.update();
    }

    protected void update() {
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
                "-fx-background-image: url('/client/resources/img/buildings/" + this.building.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 120 200"
            );

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

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

    @FXML
    public void setController(BuildController controller) {
        this.controller = controller;
    }
}
