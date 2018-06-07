package client.source.components.reward;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CatComponent extends VBox {

    @FXML private AnchorPane background;
    @FXML private AnchorPane type;

    public CatComponent() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/reward/cat_villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        this.type.getChildren().setAll(villager.getType());
//
//        this.background.setStyle(
//                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
//                        "-fx-background-repeat: stretch;" +
//                        "-fx-background-position: center center;" +
//                        "-fx-background-size: 110 200"
//        );

    }

}
