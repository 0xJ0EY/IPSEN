package server.sources.models.stories;

import server.sources.models.villagers.Villager;

import java.io.Serializable;
import java.util.ArrayList;

public class Story implements Serializable {

    private String story;
    private ArrayList<Choice> choices;
    private ArrayList<Villager> villagers;

    public Story(String story, ArrayList<Choice> choices) {
        this.story = story;
        this.choices = choices;
    }

    public String getStory() {
        return story;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public ArrayList<Villager> getVillagers() {
        return villagers;
    }

    public void setVillagers(ArrayList<Villager> villagers) {
        this.villagers = villagers;
    }
}
