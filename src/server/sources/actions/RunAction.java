package server.sources.actions;

import client.source.Client;
import client.source.controllers.VillagerSelectionController;
import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.ExploreRunNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RunAction implements ActionInterface {

    private ArrayList<Villager> selectedVillagers;

    public RunAction(ArrayList<Villager> selectedVillagers){
        this.selectedVillagers = selectedVillagers;
    }

    @Override
    public void execute(Server server) throws RemoteException {
        for (Villager villager: selectedVillagers) {
            villager.tire();
        }
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new ExploreRunNotification();
    }

}
