package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

/**
 * Class activates when the player chooses to refresh te houses.
 *
 * @author Robin Sylverio
 */
public class RefreshHousesAction implements ActionInterface {

    /**
     * Send notification to the server to refresh and redistribute.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return null;
    }

    /**
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {

    }
}
