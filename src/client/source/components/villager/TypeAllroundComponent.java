package client.source.components.villager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TypeAllroundComponent extends AnchorPane {

    public TypeAllroundComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/villager/type_allround.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}