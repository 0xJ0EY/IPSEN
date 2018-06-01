package server.sources.models;

import server.sources.models.villagers.Villager;

public class Dice {

    private int value;
    private Villager villager;

    public void roll() {
        value = (int)(Math.random() * 6 + 1);
    }

    public int returnValue() {
        return value;
    }
}
