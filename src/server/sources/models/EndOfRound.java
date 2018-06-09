package server.sources.models;

import server.sources.interfaces.PlayerBoardInterface;
import server.sources.models.buildings.Building;
import server.sources.models.perks.BedPerk;
import server.sources.models.perks.Perk;

import java.io.Serializable;
import java.util.ArrayList;

public class EndOfRound implements Serializable {

    private PlayerBoard playerBoard;

    public EndOfRound(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }


    public int countBeds() {
        ArrayList<Perk> perks = this.playerBoard.getBuildingsPerks();
        int beds = 0;

        for (Perk perk : perks) {
            if (perk instanceof BedPerk) beds++;
        }

        return beds;
    }


}
