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

    public int earn(Dice dice){

        // TODO: Explore implementation
        return 0;

    }

}