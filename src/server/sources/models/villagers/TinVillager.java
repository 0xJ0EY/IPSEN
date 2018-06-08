package server.sources.models.villagers;

import server.sources.interfaces.PlayerBoardInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class TinVillager extends Villager {

    public TinVillager(ArrayList<Lantern> lanterns, VillagerState state) throws RemoteException {
        super(lanterns, state);
    }

    @Override
    public void rest(PlayerBoardInterface playerBoard) throws RemoteException {
        super.rest(playerBoard);
    }

}
