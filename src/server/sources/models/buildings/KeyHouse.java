package server.sources.models.buildings;

import server.sources.interfaces.Perk;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class KeyHouse extends House {

    /**
     * For creating a KeyHouse object.
     * @param price
     * @param perks
     * */
    public KeyHouse(int price, ArrayList<Perk> perks){
        super(price, perks);
    }
}
