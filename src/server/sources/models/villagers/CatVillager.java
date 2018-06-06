package server.sources.models.villagers;

import server.sources.models.Dice;

import java.util.ArrayList;

public class CatVillager extends Villager {


    public CatVillager(ArrayList<Lantern> lanterns, boolean injured, boolean tired) {

        super(lanterns, injured, tired);
    }

    public boolean IsUsable(){
        Dice dice = new Dice();
        dice.roll();

        if(dice.returnValue() >= 3)
            return true;
        else
            return false;
    }

}
