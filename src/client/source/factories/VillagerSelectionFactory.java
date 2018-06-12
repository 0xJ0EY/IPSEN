package client.source.factories;

import client.source.Client;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.util.ArrayList;

/**
 * A class that creates factories for selecting multiple or single villagers
 * @author Joey de Ruiter
 */
abstract public class VillagerSelectionFactory {

    protected Client client;

    /**
     * For setting a client
     * @param client client
     * @author Joey de Ruiter
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * A declared method for implementing purposes in subclasses.
     * @return an implemented method that returns an arraylist of villagers
     * @author Joey de Ruiter
     */
    public abstract ArrayList<VillagerInterface> getVillagerList();
}
