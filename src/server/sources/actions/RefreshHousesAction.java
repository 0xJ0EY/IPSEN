package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.MarketInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.RefreshHousesNotification;

import java.rmi.RemoteException;

public class RefreshHousesAction implements ActionInterface {

    private MarketInterface market;

    @Override
    public void execute(Server server) throws RemoteException {
        server.getGameController().getMarket().refreshHousesAndOutposts();
        this.market = server.getGameController().getMarket();
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new RefreshHousesNotification(this.market);
    }
}
