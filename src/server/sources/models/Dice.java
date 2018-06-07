package server.sources.models;

import server.sources.models.villagers.Villager;

import java.io.Serializable;

public class Dice implements Serializable {

    private int value;
    private Villager villager;

    public void roll() {
        value = (int)(Math.random() * 6 + 1);
    }

    public int returnValue() {
        return value;
    }
}
