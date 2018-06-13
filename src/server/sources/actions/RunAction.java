package server.sources.actions;

import client.source.Client;
import client.source.controllers.VillagerSelectionController;
import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.ExploreRunNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The player can activate this while explore.
 *
 * @author Richard Kerkvlied
 */
public class RunAction implements VillagerActionInterface {

    private ArrayList<VillagerInterface> selectedVillagers;

    /**
     * Tires every villager that was selected.
     *
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {
        for (VillagerInterface villager: selectedVillagers) {
            villager.tire();
        }
    }

    /**
     * Activates explore Run notification.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return new ExploreRunNotification();
    }

    /**
     * @param villagers
     */
    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.selectedVillagers = villagers;
    }

}
