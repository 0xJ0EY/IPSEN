package client.source.components.villager_to_train;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.models.villagers.TrainerVillager;
import server.sources.models.villagers.Villager;

import java.io.IOException;

public class VillagerComponent extends AnchorPane {

    private Villager villager;
    @FXML AnchorPane background;
    @FXML AnchorPane type;
    @FXML
    Button trainBtn;



    public VillagerComponent(Villager villager) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager_to_train/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (villager.getClass().getSimpleName()){
            case "TrainerVillager":
                this.type.setStyle("-fx-background-image: url('/client/resources/img/type/Feather.png')");
                break;
            case "BuilderVillager":
                this.type.setStyle("-fx-background-image: url('/client/resources/img/type/Hammer.png')");
                break;
            default:
                break;
        }

        this.villager = villager;

        this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 110 200");

    }

    @FXML
    private void trainBtn(){
        System.out.println("Clicked on train button");
    }
}
