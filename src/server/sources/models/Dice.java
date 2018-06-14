package server.sources.models;

import server.sources.models.villagers.Villager;

import java.io.Serializable;

public class Dice implements Serializable {

    protected int eyes;

    public void roll() {
        eyes = (int)(Math.random() * 6 + 1);
    }

    public int getEyes() {
        return eyes;
    }
}
