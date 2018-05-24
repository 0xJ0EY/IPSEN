package server.sources.models;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class StarHouse extends House {

    /**
     * For creating a StarHouse object.
     * @param price
     * @param perks
     * */
    public StarHouse(int price, ArrayList<Perk> perks){
        super(price, perks);
    }
}
