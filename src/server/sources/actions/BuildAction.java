package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class BuildAction implements VillagerActionInterface {

    private ArrayList<Villager> villagers;

    @Override
    public void execute(Server server) throws RemoteException {

    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }

    @Override
    public void setSelectedVillagers(ArrayList<Villager> villagers) {
        this.villagers = villagers;
    }

}
