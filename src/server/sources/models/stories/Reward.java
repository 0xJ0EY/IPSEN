package server.sources.models.stories;

public class Reward {

    private String reward;

    public Reward(String reward) {
        this.reward = reward;
    }

    public void execute() {
        System.out.println(this.reward);
    }
}
