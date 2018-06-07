package client.source.components.villager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;

public class TypeAllroundComponent extends AnchorPane implements Serializable {

    public TypeAllroundComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/type_allround.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
