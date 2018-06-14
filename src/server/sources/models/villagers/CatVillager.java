package server.sources.models.villagers;

import server.sources.models.Dice;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CatVillager extends Villager {

    /**
     * creates a CatVillager.
     * @param lanterns
     * @param state
     * @param background
     * @throws RemoteException java.rmi.RemoteException
     */
    public CatVillager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        super(lanterns, state, background);
    }

    /**
     * throws a dice when using this villager.
     * higher score than 3 wil make it do the work
     * @return usable boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public boolean isUsable() throws RemoteException {
        Dice dice = new Dice();
        dice.roll();

        return dice.getEyes() >= 3;
    }

}
