package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class TradeNotification implements NotificationInterface {

    private GameClientInterface buyer;
    private GameClientInterface seller;

    public TradeNotification(GameClientInterface buyer, GameClientInterface seller){
        this.buyer = buyer;
        this.seller = seller;

    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().messageObserver.setState(
                buyer.getPlayer().getUsername() + "Has bought a good from " + seller.getPlayer().getUsername() + "."
        );
    }
}
