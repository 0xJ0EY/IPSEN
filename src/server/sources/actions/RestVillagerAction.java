package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;

/**
 * Class activates when de player lets the villager in the end of round.
 */
public class RestVillagerAction implements ActionInterface {

    /**
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {

        System.out.println("Selected villagers");

    }

    /**
     * Activates the Rest villager.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }
}
