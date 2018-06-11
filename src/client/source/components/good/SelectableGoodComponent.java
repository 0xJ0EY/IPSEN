package client.source.components.good;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Optional;

public class SelectableGoodComponent extends GoodComponent {

    private Client client;
    private int index;

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

    public void setClient(Client client) {
        this.client = client;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
