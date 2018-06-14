package server.sources.models;

import server.sources.models.perks.BedPerk;
import server.sources.models.perks.Perk;
import server.sources.models.perks.Refreshable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * EndOfRound is a wrapper for some EndOfRound methods
 */
public class EndOfRound implements Serializable {

    private PlayerBoard playerBoard;

    private ArrayList<Perk> perks;

    public EndOfRound(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void load() {
        try {
            this.perks = this.playerBoard.getBuildingsPerks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Count all the beds from buildings
     * @author Joey de Ruiter
     * @return int
     */
    public int countBeds() {
        int beds = 0;

        for (Perk perk : this.perks) {
            if (perk instanceof BedPerk) beds++;
        }

        return beds;
    }

    /**
     *
     * @return int
     */
    public int countCoins() {
        try {
            return playerBoard.getAdvancementTracker().calculateCoins();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Refresh the perks of the buildings
     */
    public void refreshPerks() throws RemoteException{
        for (Perk perk : this.perks) {
            if (perk instanceof Refreshable) {
                ((Refreshable) perk).refresh();
            }
        }
    }

}
