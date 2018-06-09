package server.sources.models.buildings;

import javafx.scene.layout.AnchorPane;
import server.sources.models.perks.Harvastable;
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
    private Harvastable harvastable = null;
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
                harvastable = ((Harvastable) perks.get(i)).getHarvestable();

            }
        }
    }

    /**
     * This is for checking if a building is for sale.
     * @param player
     * @author Robin Silvério
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

    public ArrayList<Perk> getPerks(){
        return  this.perks;
    }

    public AnchorPane getGoodComponent(){
        return good;
    }

    /**
     * This is necessary to add information on a card
     * @author Robin Silvério
     */
    @Override
    public String toString(){
        String perk = "";

        for (Perk p : perks){
            perk += p.toString() + "\n";
        }

        return "Price: " + this.price + "\n" + perk;
    }

    /**
     * Here we can retrieve only village points perks from one building
     * @return Arraylist of Village Points perks
     * @author Robin Silvério
     */
    public ArrayList<villagePointsPerk> getVillagePointsPerk() {
        ArrayList<villagePointsPerk> vpPerk = new ArrayList<villagePointsPerk>();

        for (Perk p : this.perks) {
            if (p.getClass().getSimpleName().equals("villagePointsPerk"))
                vpPerk.add((villagePointsPerk) p);
        }

        return vpPerk;
    }

    public Harvastable getHarvastable(){
        return this.harvastable;
    }
}
