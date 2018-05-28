package server.sources.models.buildings;

import server.sources.models.perks.Perk;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class Outpost extends Building {

    /**
     * For creating an outpost object.
     * @param price
     * @param perks
     * */
    public Outpost(int price, ArrayList<Perk> perks){
        super(price, perks);
    }
}
