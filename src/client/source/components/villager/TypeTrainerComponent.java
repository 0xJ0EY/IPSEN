package client.source.components.villager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TypeTrainerComponent extends AnchorPane {

    public TypeTrainerComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/type_trainer.fxml"));

        loader.setRoot(this);
        loader.setController(this);



        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
