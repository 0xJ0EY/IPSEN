package server.sources.models.buildings;

import server.sources.models.perks.Perk;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class KeyHouse extends House {

    /**
     * creates a KeyHouse.
     * @param cost
     * @param perks
     * @param background
     * @author Robin Silverio
     */
    public KeyHouse(int cost, ArrayList<Perk> perks, String background) throws RemoteException {
        super(cost, perks, background);
    }

}
