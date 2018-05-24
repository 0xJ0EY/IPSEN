package server.sources.models;

import server.sources.interfaces.Perk;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class House extends Building {

    /**
     * For creating a house object.
     * @param price
     * @param perks
     * */
    public House(int price, ArrayList<Perk> perks){
        super(price, perks);
    }
}
