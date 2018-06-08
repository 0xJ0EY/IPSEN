package server.sources.models.buildings;

import server.sources.interfaces.PlayerInterface;
import server.sources.models.perks.Perk;
import server.sources.models.Player;
import server.sources.models.perks.villagePointsPerk;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

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
    public boolean canBuy(PlayerInterface player) {
        // TODO: Check if builder can actually buy the building
        try {
            if (player.getPlayerBoard().getCoins() < price)
                return false;
            player.getPlayerBoard().payCoin(price);
            System.out.println(player.getPlayerBoard().getCoins() + "Coins remaining");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

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

    public ArrayList<villagePointsPerk> getVillagePointsPerk(){
        ArrayList<villagePointsPerk> vpPerk = new ArrayList<villagePointsPerk>();

        for (Perk p : this.perks){
            if (p.getClass().getSimpleName().equals("villagePointsPerk"))
                vpPerk.add((villagePointsPerk)p);
        }

        return vpPerk;
    }
}
