package server.sources.models;

public class Dice {

    private int value;

    public void roll() {

        value = (int)Math.random() * 6 + 1;

    }

    public int returnValue() {

        return value;

    }
}
