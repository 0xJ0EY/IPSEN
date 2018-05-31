package client.source.components.villager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TypeBuilderComponent extends AnchorPane {

    public TypeBuilderComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/villager/type_builder.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
