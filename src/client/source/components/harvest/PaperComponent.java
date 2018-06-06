package client.source.components.harvest;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PaperComponent extends AnchorPane {
    public PaperComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/harvest/paperPerk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
