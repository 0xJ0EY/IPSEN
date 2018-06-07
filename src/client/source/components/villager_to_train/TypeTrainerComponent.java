package client.source.components.villager_to_train;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.villagers.TrainerVillager;

import java.io.IOException;

public class TypeTrainerComponent extends AnchorPane {

    public TypeTrainerComponent(TrainerVillager trainer) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager_to_train/type_trainer.fxml"));

        loader.setRoot(this);
        loader.setController(this);



        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
