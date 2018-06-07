package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.TrainNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class TrainerAction implements VillagerActionInterface {

    private GameClientInterface target;
    private ArrayList<Villager> villagers;

    public TrainerAction(GameClientInterface target) {
        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {

    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TrainNotification(this.target);
    }

    @Override
    public void setSelectedVillagers(ArrayList<Villager> villagers) {
        this.villagers = villagers;
    }

}
