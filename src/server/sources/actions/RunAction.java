package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.ExploreRunNotification;

import java.rmi.RemoteException;

public class RunAction implements ActionInterface {

    @Override
    public void execute(Server server) throws RemoteException {
//        TODO: Exhaust villagers


    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new ExploreRunNotification();
    }

}
