package server.sources.models.stories;

import java.io.Serializable;
import java.util.ArrayList;

public class Option implements Serializable {

    private int cost;
    private ArrayList<Reward> rewards = new ArrayList<Reward>();

    public Option(int cost, ArrayList<Reward> rewards) {
        this.cost = cost;
        this.rewards = rewards;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }
}
