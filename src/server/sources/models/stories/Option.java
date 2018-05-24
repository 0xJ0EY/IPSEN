package server.sources.models.stories;

import java.util.ArrayList;

public class Option {

    private int cost;
    private ArrayList<Reward> rewards = new ArrayList<Reward>();

    public Option(int cost, ArrayList<Reward> rewards) {
        this.cost = cost;
        this.rewards = rewards;
    }
}
