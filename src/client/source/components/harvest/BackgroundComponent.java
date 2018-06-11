package client.source.components.harvest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;

public class BackgroundComponent extends AnchorPane implements Serializable {

    @FXML private AnchorPane background;

    public BackgroundComponent(String component) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/harvest/backgroundPerk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        background.setStyle("-fx-background-color: " + component + "");

    }
}
