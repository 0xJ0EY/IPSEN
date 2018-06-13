package client.source.components.good;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.Good;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class that loads all goods
 * Created by Joey de Ruiter
 */
public class GoodComponent extends AnchorPane {

    protected Good good;

    @FXML public AnchorPane background;

    /**
     * Loads a fxml file and activates it.
     * For loading goodscomponents
     * @author Joey de Ruiter
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
     * Setting a model
     * @param good Good
     * @author Robin Silverio
     */
    public void setModel(Good good) {
        this.good = good;
    }
}
