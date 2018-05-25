package server.sources.models.stories;

import java.io.Serializable;

public class Reward implements Serializable {

    private String reward;

    public Reward(String reward) {
        this.reward = reward;
    }

    public void execute() {
        System.out.println(this.reward);
    }

    public String getReward() {
        return reward;
    }
}
