package client.source.components.harvest;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;

/**
 * A class that creates clay pot good component
 * Created by Richard Kerkvliet
 */
public class ClayPotComponent extends AnchorPane implements Serializable {
    public ClayPotComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/harvest/clayPotPerk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
