package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.MarketInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class RefreshHousesNotification implements NotificationInterface {

    private MarketInterface market;

    public RefreshHousesNotification(MarketInterface market) {
        this.market = market;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().marketObserver.setState(this.market);
    }
}
