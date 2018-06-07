package client.source.components.harvest;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MushroomComponent extends AnchorPane {


    public MushroomComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/harvest/mushroomPerk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
