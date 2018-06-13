package server.sources.notifications;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import server.sources.actions.AcceptAction;
import server.sources.controllers.GoodOnSale;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.GoodOnSaleInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;
import java.util.Optional;

public class TradeGoodNotification implements NotificationInterface {

    private int bid;
    private int index;
    private GoodOnSaleInterface goodOnSale;
    private GameClientInterface buyer;

    public TradeGoodNotification(int bid, GoodOnSaleInterface goodOnSale, int index, GameClientInterface buyer){
        this.bid = bid;
        this.goodOnSale = goodOnSale;
        this.index = index;
        this.buyer = buyer;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Trade Request!");
            alert.setHeaderText("A player wants to buy your good for: " + bid + " coins.");
            alert.setContentText("Are you OK with this?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                try {
                    gameClient.getServer().getGameController().getMarket().buyGood(this.index);
                    gameClient.getServer().executeAction(new AcceptAction(this.buyer, this.goodOnSale, this.bid));
                } catch (RemoteException r){
                    r.printStackTrace();
                }

            }
        });
    }
}
