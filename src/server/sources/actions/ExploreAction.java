package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.ExploreNotification;

import java.rmi.RemoteException;

public class ExploreAction implements ActionInterface {


    @Override
    public NotificationInterface update() throws RemoteException {
        return new ExploreNotification();
    }

    @Override
    public void execute(Server server) throws RemoteException {
        System.out.println("Dit is een explore actie");
    }
}
