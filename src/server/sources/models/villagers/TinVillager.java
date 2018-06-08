package server.sources.models.villagers;

import server.sources.interfaces.PlayerBoardInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class TinVillager extends Villager {

    public TinVillager(ArrayList<Lantern> lanterns, VillagerState state) throws RemoteException {
        super(lanterns, state);
    }

    /**
     * Same as the normal sleep, but don't use a bed from the player board
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public void sleep() throws RemoteException {
        if (state == VillagerState.USABLE) return;

        // Tired -> usable
        if (state == VillagerState.TIRED) this.state = VillagerState.USABLE;

        // Injured -> Tired
        if (state == VillagerState.INJURED) this.state = VillagerState.TIRED;

        this.slept = true;
    }
}
