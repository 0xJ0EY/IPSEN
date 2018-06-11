package client.source.components.reward;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.rmi.RemoteException;

public abstract class RewardComponent<T> extends AnchorPane {

    @FXML protected AnchorPane background;

    public RewardComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/reward/reward.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void load() throws RemoteException;

    public abstract T getModel();

    public abstract void setModel(T obj);
}
