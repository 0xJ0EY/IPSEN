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

public class BuildAction implements VillagerActionInterface {

    private GameClientInterface target;
    private ArrayList<VillagerInterface> villagers;

    public BuildAction(GameClientInterface target) {
        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {
        for (VillagerInterface villager: villagers) {
            villager.tire();
        }
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new BuildNotification(this.target);
    }

    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.villagers = villagers;
    }

}
