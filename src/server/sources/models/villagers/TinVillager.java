package server.sources.models.villagers;

import java.util.ArrayList;

public class TinVillager extends Villager {

    public TinVillager(ArrayList<Lanterns> lanterns, boolean injured, boolean tired) {
        super(lanterns, injured, tired);
    }

    public void rest() {
        if (this.injured) {
            this.injured = false;
            this.tired = true;
        }
        else if(tired) {
            this.tired = false;
        }
    }
}
