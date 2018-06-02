package server.sources.models.villagers;

import server.sources.models.Dice;

import java.io.Serializable;

public class Lantern implements Serializable {

    private int cost;
    private int amount;

    public Lantern(int cost, int amount) {
        this.cost = cost;
        this.amount = amount;
    }

    public int getAmount(int thrown) {

        // TODO: 02/06/2018 think this one is done 
        
        if (thrown>this.cost){
            return amount;
        }else{
            return 0;
        }
    }
}