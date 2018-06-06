package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;

public class RestVillagerAction implements ActionInterface {

    @Override
    public void execute(Server server) throws RemoteException {

        System.out.println("Selected villagers");

    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }
}
