package server.sources.models.villagers;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class OilGirlVillager extends Villager {

    public OilGirlVillager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        super(lanterns, state, background);
    }

    //TODO: fixing method
    public void execute() {
//        player.reputationDown();
    }

}
