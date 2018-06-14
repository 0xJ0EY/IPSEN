package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.BuildNotification;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * clicks on the build action in the turn.
 *
 * @author Joey de Ruiter
 */
public class BuildAction implements VillagerActionInterface {

    private GameClientInterface target;
    private ArrayList<VillagerInterface> villagers;

    /**
     * @param target
     */
    public BuildAction(GameClientInterface target) {
        this.target = target;
    }

    /**
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {
//        for (VillagerInterface villager: villagers) {
//            villager.tire();
//        }
    }

    /**
     * Activates build action and allows everybody to see it.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return new BuildNotification(this.target, this.villagers);
    }

    /**
     * @param villagers
     */
    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.villagers = villagers;
    }

}
