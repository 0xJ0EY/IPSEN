package client.source.views;

import client.source.components.villager.LanternController;
import client.source.interfaces.ControllerInterface;
import client.source.interfaces.ViewInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LanternView extends AnchorPane implements ViewInterface {

    @FXML private AnchorPane background;

    private int lanterns = 0;

    @Override
    public void load(ControllerInterface controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/lanterns.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLanterns(int lanterns) {
        this.lanterns = lanterns;
    }

    public void show() {
        String image = "/client/resources/img/lanterns/lanterns_" + this.lanterns + ".png";
        this.background.setStyle("-fx-background-image: url('" + image + "')");
    }

}
