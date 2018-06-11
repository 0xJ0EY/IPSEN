package client.source.components.party;

import client.source.controllers.ExplorePartyController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.Dice;
import server.sources.models.villagers.Villager;

import java.io.IOException;
import java.rmi.RemoteException;

public class PartyVillagerComponent extends VBox {

    private ExplorePartyController explorePartyController;

    private VillagerInterface villager;
    private Dice dice;

    @FXML private VBox villagerBox;
    @FXML private AnchorPane background;
    @FXML private AnchorPane type;
    @FXML private Button roll;
    @FXML private Label rolled;

    public PartyVillagerComponent(VillagerInterface villager, ExplorePartyController explorePartyController) throws RemoteException {
        this.villager = villager;
        this.dice = new Dice();
        this.explorePartyController = explorePartyController;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/party/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-position: center center;" +
                        "-fx-background-size: 110 200"
        );


        this.roll.setOnMouseClicked( e -> {
            dice.roll();
            this.rolled.setText("You rolled: " + dice.returnValue());

            try {
                this.explorePartyController.updateLanternScore(this.villager.calculateLanters(dice));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            roll.setDisable(true);
            }
        );
    }
}
