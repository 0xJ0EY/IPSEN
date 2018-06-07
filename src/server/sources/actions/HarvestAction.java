package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HarvestAction implements VillagerActionInterface {

    private GameClientInterface target;
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

    }

    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.selectedVillagers = villagers;
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }


}
