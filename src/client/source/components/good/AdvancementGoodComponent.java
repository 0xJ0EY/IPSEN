package client.source.components.good;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * A class that loads all goods in advancement track
 * Created by Joey de Ruiter
 */
public class AdvancementGoodComponent extends GoodComponent {

    private int amount;
    private int index;
    private Client client;

    @FXML private Text amountText;
    @FXML private Text pointsText;

    /**
     * For loading goodscomponents in advancement track
     * @author Joey de Ruiter
     */
    public AdvancementGoodComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/good/advancement_good.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates amount of that good
     * @author Joey de Ruiter
     */
    private void updateAmountText() {
        this.amountText.setText(Integer.toString(this.amount));
    }

    /**
     * Updates amount of point that a user can obtain
     */
    private void updatePointsText() {
        int points = 0;

        try {
            points = this.client.getGameClient().getPlayer().getPlayerBoard().getAdvancementTracker().getPointsByIndex(this.index);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.pointsText.setText(Integer.toString(points));
    }

    public void setAmount(int amount) {
        this.amount = amount;

        this.updateAmountText();
    }

    public void setIndex(int index) {
        this.index = index;

        this.updatePointsText();
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
