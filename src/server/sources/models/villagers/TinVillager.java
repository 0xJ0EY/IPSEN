package server.sources.models.villagers;

import server.sources.interfaces.PlayerBoardInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class TinVillager extends Villager {

    /**
     * creates a TinVillager
     * @param lanterns
     * @param state
     * @param background
     * @throws RemoteException java.rmi.RemoteException
     */
    public TinVillager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        super(lanterns, state, background);
    }

    /**
     * returns if the villager can use a cider.
     *
     * @return canusecider boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public boolean canUseCider() throws RemoteException {
        return false;
    }

    /**
     * returns if the villager can use a potion.
     *
     * @return canUsePotion
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public boolean canUsePotion() throws RemoteException {
        return false;
    }

    /**
     * Same as the normal sleep, but don't use a bed from the player board.
     * @author Joey de Ruiter
     * @throws RemoteException java.rmi.RemoteException
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
