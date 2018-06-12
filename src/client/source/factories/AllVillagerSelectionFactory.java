package client.source.factories;

import client.source.Client;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A Class that allows to create selectable villagers
 * Created by Joey de Ruiter
 */
public class AllVillagerSelectionFactory extends VillagerSelectionFactory {

    protected Client client;

    /**
     * Setting a client.
     * @author Joey de Ruiter
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Getting a villager arraylist.
     * @return An arrayList of villagers
     * @author Jan Douwe Sminia
     */
    public ArrayList<VillagerInterface> getVillagerList() {
        try {
            return client.getGameClient().getPlayer().getPlayerBoard().listAvailableVillagers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }
}
