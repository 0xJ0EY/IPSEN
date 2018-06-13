package server.sources.models.stories;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates an Option.
 * @author Richard Kerkvliet
 */
public class Option implements Serializable {

    private int cost;
    private ArrayList<Reward> rewards = new ArrayList<Reward>();

    /**
     * creates an Option
     * @param cost
     * @param rewards
     * @author Richard Kerkvliet
     */
    public Option(int cost, ArrayList<Reward> rewards) {
        this.cost = cost;
        this.rewards = rewards;
    }

    /**
     * returns the cost in lanterns of the option.
     * @return int cost
     * @author Richard Kerkvliet
     */
    public int getCost() {
        return cost;
    }

    /**
     * returns the Rewards in the Option
     * @return Reward ArrayList
     * @author Richard Kerkvliet
     */
    public ArrayList<Reward> getRewards() {
        return rewards;
    }
}
