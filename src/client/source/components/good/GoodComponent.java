package client.source.components.good;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.Good;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * This class is an component that can can be loaded in a controller.
 *
 * @author Jan Douwe Sminia
 */
public class GoodComponent extends AnchorPane {

    protected Good good;

    @FXML public AnchorPane background;

    /**
     * Loads a fxml file and activates it.
     */
    public GoodComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/good/good.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the style for the component.
     */
    public void load()  {
        this.background.setStyle(
            "-fx-background-image: url('/client/resources/img/goods/" + this.good.getBackground() + " ');" +
            "-fx-background-repeat: stretch;" +
            "-fx-background-position: center center;" +
            "-fx-background-size: 50 50"
        );
    }

    /**
     * @param good
     */
    public void setModel(Good good) {
        this.good = good;
    }
}
