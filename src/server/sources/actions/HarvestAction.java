package server.sources.actions;

import client.source.components.reward.GoodRewardComponent;
import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;

import server.sources.interfaces.VillagerInterface;
import server.sources.notifications.EndOfTurnNotification;
import server.sources.notifications.HarvestRewardNotification;
import server.sources.notifications.ShowHarvestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class is called when the player chooses the Harvest Action.
 *
 * @author Jan Douwe Sminia
 */
public class HarvestAction implements VillagerActionInterface {

    private GameClientInterface target;
    private int count = 0;

    private ArrayList<VillagerInterface> selectedVillagers;

    /**
     * Sets the right client.
     *
     * @param target
     * @author Jan Douwe
     */
    public HarvestAction(GameClientInterface target){
        this.target = target;
    }

    /**
     * Counts the amount of times it was activated.
     *
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {
        this.count++;
    }

    /**
     * Allows all players to see this action and activates the action.
     * Uses count to show harvest the right amount of times.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        if (this.count <= this.selectedVillagers.size()) {
            // Stuur alle spelers naar de harvest view
            return new ShowHarvestNotification(this);

        } else {
            // Stuur alle spelers naar de above view (we zijn klaar).
            // TODO: 14/06/2018 add goodRewardComponent and remove endturnnotification
            //return new HarvestRewardNotification(new GoodRewardComponent());
            return new EndOfTurnNotification();

        }
    }

    /**
     * @param villagers
     */
    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.selectedVillagers = villagers;
    }

}
