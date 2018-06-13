package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.EndOfTurnNotification;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;

/**
 * Activates the en of turn action for everyone.
 */
public class EndTurnAction implements ActionInterface {

    @Override
    public void execute(Server server) throws RemoteException {

    }

    /**
     * Activates end of turn.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return new EndOfTurnNotification();
    }

}
