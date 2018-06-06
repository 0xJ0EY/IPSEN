package server.sources.models.buildings;

import javafx.scene.layout.AnchorPane;
import server.sources.models.perks.Harvastable;
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
    private AnchorPane good = null;

    /**
     * For creating a building object.
     * @param price
     * @param perks
     * */
    public Building(int price, ArrayList<Perk> perks) {
        this.price = price;
        this.perks = perks;

        for (int i = 0; i < perks.size(); i++) {
            if(perks.get(i)instanceof Harvastable){
                good = ((Harvastable) perks.get(i)).getGoodComponent();
            }
        }
    }

    /**
     * This is for checking if a building is for sale.
     * @param player
     * */
    public boolean canBuy(Player player) {
        // TODO: Check if builder can actually buy the building
        return true;
    }

    public ArrayList<Perk> getPerks(){
        return  this.perks;
    }

    public AnchorPane getGoodComponent(){
        return good;
    }

}
