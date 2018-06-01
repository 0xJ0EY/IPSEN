package client.source.components.party;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.sources.models.Dice;
import server.sources.models.villagers.Villager;

import java.io.IOException;

public class PartyVillagerComponent extends VBox {

    private Villager villager;
    private Dice dice;

    @FXML VBox villagerBox;
    @FXML AnchorPane background;
    @FXML AnchorPane type;
    @FXML Button roll;

    public PartyVillagerComponent(Villager villager) {
        this.villager = villager;
        this.dice = new Dice();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/party/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.type.getChildren().setAll(villager.getType());

        this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-position: center center;" +
                        "-fx-background-size: 110 200"
        );

        this.roll = new Button();
        roll.setText("ROLL DICE");
        roll.setPrefWidth(110);
        roll.setPrefHeight(50);

        this.roll.setOnMouseClicked( e -> {
            dice.roll();
            System.out.println(dice.returnValue());
                }
        );

        villagerBox.getChildren().add(roll);
    }
}
