package server.sources.models.villagers;

import server.sources.models.Dice;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CatVillager extends Villager {

    public CatVillager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        super(lanterns, state, background);
    }

    @Override
    public boolean isUsable() throws RemoteException {
        Dice dice = new Dice();
        dice.roll();

        return dice.returnValue() >= 3;
    }

}
