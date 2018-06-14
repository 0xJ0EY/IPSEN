package client.source.models;

public class DisplayDice {

    private int value;

    public DisplayDice(int value) {
        this.value = value;
    }

    public int read() {
        return value;
    }
}
