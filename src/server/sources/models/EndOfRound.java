package server.sources.models;

import server.sources.interfaces.PlayerBoardInterface;
import server.sources.models.buildings.Building;
import server.sources.models.perks.BedPerk;
import server.sources.models.perks.Perk;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class EndOfRound implements Serializable {

    private PlayerBoard playerBoard;

    public EndOfRound(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }


    public int countBeds() {
        ArrayList<Perk> perks = new ArrayList<Perk>();
        int beds = 0;

        try {
            perks = this.playerBoard.getBuildingsPerks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (Perk perk : perks) {
            if (perk instanceof BedPerk) beds++;
        }

        return beds;
    }


}
