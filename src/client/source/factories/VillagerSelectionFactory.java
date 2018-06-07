package client.source.factories;

import client.source.Client;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.util.ArrayList;

abstract public class VillagerSelectionFactory {

    protected Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public abstract ArrayList<VillagerInterface> getVillagerList();
}
