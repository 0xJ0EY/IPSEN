package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.notifications.EndOfTurnNotification;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Activates the en of turn action for everyone.
 */
public class EndTurnAction implements ActionInterface {

    private ArrayList<VillagerInterface> villagers;

    public EndTurnAction(ArrayList<VillagerInterface> villagers){
        this.villagers = villagers;
    }

    @Override
    public void execute(Server server) throws RemoteException {
        for (VillagerInterface villager: villagers) {
            villager.tire();
        }
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
