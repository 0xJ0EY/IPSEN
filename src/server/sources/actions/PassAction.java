package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.PassNotification;

import java.rmi.RemoteException;

/**
 * This is activated  when you choose pass action.
 *
 * @author Richard Kerkvlied
 */
public class PassAction implements ActionInterface {

    private GameClientInterface target;

    public PassAction(GameClientInterface target) throws RemoteException{
        this.target = target;
        target.getClient().showMain();
    }

    /**
     * Activates when at the bigeinning.
     *
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {
        target.getPlayer().pass();
    }

    /**
     * Activates for everyone.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return new PassNotification();
    }

}
