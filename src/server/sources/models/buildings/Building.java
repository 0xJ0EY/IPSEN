package server.sources.models.buildings;

import javafx.scene.layout.HBox;
import server.sources.interfaces.Perk;
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
    public Building(String buildingName, int price, ArrayList<Perk> perks) {
        this.buildingName = buildingName;
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
}
