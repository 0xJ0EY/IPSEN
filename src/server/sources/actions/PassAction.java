package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.PassNotification;

import java.rmi.RemoteException;

public class PassAction implements ActionInterface {

    private GameClientInterface target;

    public PassAction(GameClientInterface target) {
        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {
        target.getPlayer().pass();
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new PassNotification();
    }

}
