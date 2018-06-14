package client.source.views;

import client.source.interfaces.ControllerInterface;
import client.source.interfaces.ViewInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DiceView extends AnchorPane implements ViewInterface {

    @FXML private AnchorPane background;

    private int eyes;

    @Override
    public void load(ControllerInterface controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/dice.fxml"));

        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }

    @Override
    public void show() {
        // Redraw CSS
        String image = "/client/resources/img/dice/dice_" + this.eyes + ".png";
        this.background.setStyle("-fx-background-image: url('" + image + "')");
    }
}
