package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;

public class TestAction implements ActionInterface {

    @Override
    public void execute(Server server) throws RemoteException {
        System.out.println("Dit is een test");
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }
}
