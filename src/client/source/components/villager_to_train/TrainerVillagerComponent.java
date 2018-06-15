package client.source.components.villager_to_train;

import client.source.components.villager.SingleSelectableVillagerComponent;
import client.source.controllers.TrainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import server.sources.interfaces.PlayerBoardInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class TrainerVillagerComponent extends SingleSelectableVillagerComponent {

    private int price = 0;

    @FXML private Text priceText;

    /**
     * For selecting villagers
     * @author Joey de Ruiter
     */
    @FXML
    public void onClickSelect() {
        if (!this.controller.hasTurn()) return;
        this.deselectVillagers();

        try {
            PlayerBoardInterface playerBoard = this.controller.getClient().getGameClient().getPlayer().getPlayerBoard();

            if (playerBoard.getCoins() < this.price) {
                this.controller.showMessage("Insufficient funds.");
                return;
            }

            super.onClickSelect();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/villager_to_train.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;

        this.priceText.setText(Integer.toString(this.price));
    }

    public static int calculatePrice(int index) {
        switch (index) {
            case 0:
                return 2;
            case 1:
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            default:
                return 0;
        }
    }
}
