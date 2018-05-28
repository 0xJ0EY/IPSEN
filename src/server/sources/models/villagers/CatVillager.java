package server.sources.models;

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
