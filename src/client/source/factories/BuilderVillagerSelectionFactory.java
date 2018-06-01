package client.source.factories;

import client.source.Client;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class BuilderVillagerSelectionFactory extends VillagerSelectionFactory {

    protected Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Villager> getVillagerList() {
        try {
            return client.getGameClient().getPlayer().getPlayerBoard().listAvailableBuilderVillagers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }
}