package server.sources.models.buildings;

import server.sources.models.perks.Perk;
import server.sources.models.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class Building implements Serializable {

    private String buildingName;
    private int price;
    private ArrayList<Perk> perks;

    /**
     * For creating a building object.
     * @param price
     * @param perks
     * */
    public Building(int price, ArrayList<Perk> perks) {
        this.price = price;
        this.perks = perks;
    }

    /**
     * This is for checking if a building is for sale.
     * @param player
     * */
    public boolean canBuy(Player player) {
        // TODO: Check if builder can actually buy the building
        return true;
    }

    /**
     * This is necessary to add information on a card
     */
    @Override
    public String toString(){
        String perk = "";

        for (Perk p : perks){
            perk += p.toString() + "\n";
        }

        return "Price: " + this.price + "\n" + perk;
    }
}
