package server.sources.models.villagers;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class OilGirlVillager extends Villager {

    /**
     * creates a OilGirlVillager.
     * @param lanterns
     * @param state
     * @param background
     * @throws RemoteException java.rmi.RemoteException
     */
    public OilGirlVillager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        super(lanterns, state, background);
    }

}
