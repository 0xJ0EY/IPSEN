package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.TrainNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Is called when the player chooses the train action.
 *
 * @author Robin Sylverio
 */
public class TrainerAction implements VillagerActionInterface {

    private GameClientInterface target;
    private ArrayList<VillagerInterface> villagers;

    /**
     * @param target
     */
    public TrainerAction(GameClientInterface target) {
        this.target = target;
    }

    /**
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {

    }

    /**
     * Activates the train action and shows it to everyone.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return new TrainNotification(this.target);
    }

    /**
     * @param villagers
     */
    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.villagers = villagers;
    }

}
