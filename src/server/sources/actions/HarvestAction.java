package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;

import server.sources.interfaces.VillagerInterface;
import server.sources.notifications.EndOfTurnNotification;
import server.sources.notifications.ShowHarvestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HarvestAction implements VillagerActionInterface {

    private GameClientInterface target;
    private int count = 0;

    private ArrayList<VillagerInterface> selectedVillagers;

    /**
     * geeft de client van de speler die het uitvoert mee
     *
     * @param target
     * @author Jan Douwe
     */
    public HarvestAction(GameClientInterface target){
        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {
        count++;
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        if (this.count <= this.selectedVillagers.size()) {
            // Stuur alle spelers naar de harvest view
            return new ShowHarvestNotification(this);

        } else {
            // Stuur alle spelers naar de above view (we zijn klaar).
            return new EndOfTurnNotification();

        }
    }

    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.selectedVillagers = villagers;
    }

}
