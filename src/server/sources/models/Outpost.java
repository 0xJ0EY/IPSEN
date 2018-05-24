package server.sources.models;

import server.sources.interfaces.Perk;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class Outpost extends Building {
    public Outpost(int price, ArrayList<Perk> perks){
        super(price, perks);
    }
}
