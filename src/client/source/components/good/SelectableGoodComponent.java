package client.source.components.good;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * A class that loads all selectable goods.
 * Created by Joey de Ruiter
 */
public class SelectableGoodComponent extends GoodComponent {

    private Client client;
    private int index;

    /**
     * Loads all selectable goods in above view.
     * Created by Joey de Ruiter
     */
    public SelectableGoodComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/good/selectable_good.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * For selecting goods to be placed in advancement tracker.
     * @author Joey de Ruiter
     */
    @FXML
    private void onClick() {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);

        dialog.setTitle("Move good to the advancement tracker");
        dialog.setHeaderText("Move good to the advancement tracker");
        dialog.setContentText("Do you want to move this good to the advancement tracker?");

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                this.client.getGameClient().getPlayer().getPlayerBoard().moveGoodToAdvancementTracker(this.index);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onHoverEnter() {
        this.setOpacity(0.6);
    }

    @FXML
    private void onHoverExit() {
        this.setOpacity(1);
    }

    /**
     * For setting a client
     * @param client Client
     * @author Joey de Ruiter
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * For setting a index
     * @param index int
     * @author Robin Silverio
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
