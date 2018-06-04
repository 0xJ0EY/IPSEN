package server.sources.models.villagers;

import java.io.Serializable;

public class Lantern implements Serializable {

    private int cost;
    private int amount;

    public Lantern(int cost, int amount) {
        this.cost = cost;
        this.amount = amount;
    }

    public int getAmount(int thrown) {
        if (thrown>this.cost){
            return amount;
        }else{
            return 0;
        }
    }
}