package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

/**
 * Activates at the end of an explire action.
 *
 * @author Richard Kerkvlied
 */
public class RewardAction implements ActionInterface {

    /**
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return null;
    }

    @Override
    public void execute(Server server) throws RemoteException {

    }
}
