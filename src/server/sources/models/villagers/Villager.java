package server.sources.models.villagers;

import server.sources.models.Dice;

public class Villager {

    Lanterns[] lanterns = new Lanterns[6];
    boolean injured;
    boolean tired;

    public Villager(){

    }

    //TODO: explore implementeren
    public void rolDiceForLanterns(Dice dice){

    }

    //TODO: moet nog bedden weghalen
    public void rest(){
        if(injured){
            injured = false;
            tired = true;
        }
        else if(tired){
            tired = false;
        }
    }

    public boolean isUseable(){
        if(!injured && !tired)
            return true;
        else
            return false;
    }

    public void  tire(){
        tired = true;
    }

    public void injure(){
        injured = false;
    }

}
