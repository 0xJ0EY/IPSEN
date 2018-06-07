package server.sources.models.villagers;

import server.sources.models.Dice;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CatVillager extends Villager {

    public CatVillager(ArrayList<Lantern> lanterns, VillagerState state) throws RemoteException {
        super(lanterns, state);
    }

    public boolean IsUsable() throws RemoteException {
        Dice dice = new Dice();
        dice.roll();

        if(dice.returnValue() >= 3)
            return true;
        else
            return false;
    }

}
