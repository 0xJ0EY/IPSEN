package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.RefreshHousesNotification;

import java.rmi.RemoteException;

public class RefreshHousesAction implements ActionInterface {
    @Override
    public NotificationInterface update() throws RemoteException {
        return new RefreshHousesNotification();
    }

    @Override
    public void execute(Server server) throws RemoteException {
        System.out.println("refresh houses");
        server.getGameController().getMarket().refreshHousesAndOutposts();
    }
}
