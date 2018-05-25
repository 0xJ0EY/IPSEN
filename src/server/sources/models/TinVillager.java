package server.sources.models;

public class TinVillager extends Villager{

    public void rest() {
        if(injured){
            injured = false;
            tired = true;
        }
        else if(tired){
            tired = false;
        }
    }
}
