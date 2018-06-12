package server.sources.models.buildings;

import server.sources.models.perks.Perk;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class Outpost extends Building {

    /**
     * creates a Outpost.
     * @param cost
     * @param perks
     * @param background
     * @author Robin Silvério
     */
    public Outpost(int cost, ArrayList<Perk> perks, String background) {
        super(cost, perks, background);
    }

}
