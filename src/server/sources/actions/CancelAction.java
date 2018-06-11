package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.CancelNotification;

import java.rmi.RemoteException;

public class CancelAction implements ActionInterface {

    @Override
    public void execute(Server server) throws RemoteException {

    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new CancelNotification();
    }
}
