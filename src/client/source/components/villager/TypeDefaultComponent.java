package client.source.components.villager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TypeDefaultComponent extends AnchorPane {

    public TypeDefaultComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/villager/type_default.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}