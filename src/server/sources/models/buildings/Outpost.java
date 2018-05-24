package server.sources.models.buildings;

import server.sources.interfaces.Perk;
import server.sources.models.buildings.Building;

import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class Outpost extends Building {
    public Outpost(int price, ArrayList<Perk> perks){
        super(price, perks);
    }
}
