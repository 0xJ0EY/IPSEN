package client.source.components.villager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.models.villagers.Villager;

import java.io.IOException;

public class VillagerComponent extends AnchorPane {

    private Villager villager;

    private boolean selected;

    @FXML AnchorPane background;

    @FXML Text labelType;

    @FXML AnchorPane type;

    @FXML
    private void onClickSelect() {
        this.selected = !this.selected;

        if(this.selected){
            showIndicator();
        } else {
            hideIndicator();
        }
    }

    public void setModel(Villager villager) {
        this.villager = villager;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.type.getChildren().setAll(villager.getType());

        this.background.setStyle(
            "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 110 200"
        );
    }

    public boolean isSelected() {
        return this.selected;
    }

    public Villager getVillager() {
        return villager;
    }

    private void showIndicator(){
        this.background.setStyle(
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);" +
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 115 205");

    }

    private void hideIndicator(){
        this.background.setStyle(
                "-fx-effect: dropshadow(three-pass-box, white, 00, 0, 0, 0);" +
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 110 200");

    }
}
