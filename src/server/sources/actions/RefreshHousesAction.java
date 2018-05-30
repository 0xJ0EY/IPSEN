package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class RefreshHousesAction implements ActionInterface {
    @Override
    public NotificationInterface update() throws RemoteException {
        return null;
    }

    @Override
    public void execute(Server server) throws RemoteException {

    }
}
