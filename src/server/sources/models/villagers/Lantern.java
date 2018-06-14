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

    public int throwForLanterns(Dice dice) {
        return (dice.getEyes() >= this.cost) ? amount : 0;

    }

    public int getCost() {
        return this.cost;
    }

    public int getAmount() {
        return this.amount;
    }
}