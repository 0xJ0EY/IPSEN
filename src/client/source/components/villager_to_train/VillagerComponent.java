package client.source.components.villager_to_train;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.AllroundVillager;
import server.sources.models.villagers.TrainerVillager;
import server.sources.models.villagers.Villager;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class VillagerComponent extends AnchorPane {

    private Client client;
    private int price = (int) (Math.random()* 20) + 1;
    private VillagerInterface villager;
    private Boolean selected = false;
    @FXML private AnchorPane background;
    @FXML private AnchorPane type;

    @FXML
    public void onClickSelect(ArrayList<VillagerComponent> villagerComponents) {
        for (VillagerComponent villager: villagerComponents) {
            villager.selected = false;
            villager.hideIndicator();
        }
        this.selected = true;
        this.showIndicator();
    }

    public VillagerComponent(VillagerInterface villager) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager_to_train/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.villager = villager;

        try {
            this.type.getChildren().setAll(this.villager.getType());

            this.background.setStyle(
                    "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                    "-fx-background-repeat: stretch;" +
                    "-fx-background-position: center center;" +
                    "-fx-background-size: 110 200");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void showIndicator(){
        try {
            this.background.setStyle(
                    "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);" +
                            "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                            "-fx-background-repeat: stretch;" +
                            "-fx-background-position: center center;" +
                            "-fx-background-size: 115 205"
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void hideIndicator(){
        try {
            this.background.setStyle(
                    "-fx-effect: dropshadow(three-pass-box, white, 00, 0, 0, 0);" +
                            "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                            "-fx-background-repeat: stretch;" +
                            "-fx-background-position: center center;" +
                            "-fx-background-size: 110 200"
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public VillagerInterface getVillager() {
        return this.villager;
    }
}
