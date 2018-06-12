package server.sources.models.stories;

import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates a story.
 * @author Richard Kerkvliet
 */
public class Story implements Serializable {

    private String story;
    private ArrayList<Choice> choices;
    private ArrayList<VillagerInterface> villagers;

    /**
     * Creates a story.
     * @param story
     * @param choices
     * @author Richard Kerkvliet
     */
    public Story(String story, ArrayList<Choice> choices) {
        this.story = story;
        this.choices = choices;
    }

    /**
     * Returns the current story.
     * @return String Story
     * @author Richard Kerkvliet
     */
    public String getStory() {
        return story;
    }

    /**
     * Returns the choices paired with the current story.
     * @return ArrayList Choices
     * @author Richard Kerkvliet
     */
    public ArrayList<Choice> getChoices() {
        return choices;
    }

    /**
     * Returns the villagers which are exploring the story.
     * @return ArrayList VillagerInterface
     * @author Richard Kerkvliet
     */
    public ArrayList<VillagerInterface> getVillagers() {
        return villagers;
    }

    /**
     * Pairs the villagers which are exploring with the story.
     * @param villagers
     * @author Richard Kerkvliet
     */
    public void setVillagers(ArrayList<VillagerInterface> villagers) {
        this.villagers = villagers;
    }
}
