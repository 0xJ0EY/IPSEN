package server.sources.models.villagers;

import server.sources.models.Dice;

import java.util.ArrayList;

public class Villager {

    protected ArrayList<Lanterns> lanterns = new ArrayList<Lanterns>();
    protected boolean injured;
    protected boolean tired;

    public Villager(ArrayList<Lanterns> lanterns, boolean injured, boolean tired) {
        this.lanterns = lanterns;
        this.injured = injured;
        this.tired = tired;
    }

    public void rollDiceForLanterns() {
        Dice dice = new Dice();
    }

    //TODO: moet nog bedden weghalen
    public void rest(){
        if (injured) {
            injured = false;
            tired = true;
        } else if (tired) {
            tired = false;
        }
    }

    public boolean isUseable(){
        return (!injured && !tired);
    }

    public void tire(){
        tired = true;
    }

    public void injure(){
        injured = false;
    }

}
