package server.sources.models.villagers;

import server.sources.models.Dice;

public class CatVillager extends Villager {

    Dice dice;

    public boolean IsUsable(){

        dice.roll();

        if(dice.returnValue() >= 3)
            return true;
        else
            return false;
    }

}
