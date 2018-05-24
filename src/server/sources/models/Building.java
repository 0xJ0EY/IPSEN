package server.sources.models;

import server.sources.interfaces.Perk;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public abstract class Building {
    private int price;
    private ArrayList<Perk> perks;

    /**
     * For creating a building object.
     * @param price
     * @param perks
     * */
    public Building(int price, ArrayList<Perk> perks){
        this.perks = perks;
    }

    /**
     * This is for checking if a building is for sale.
     * @param player
     * */
    public boolean canBuy(Player player) {
        return false;
    }
}
