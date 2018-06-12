package server.sources.models.buildings;

import server.sources.models.perks.Perk;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class KeyHouse extends House {

    /**
     * creates a KeyHouse.
     * @param cost
     * @param perks
     * @param background
     * @author Robin Silvério
     */
    public KeyHouse(int cost, ArrayList<Perk> perks, String background) {
        super(cost, perks, background);
    }

}
