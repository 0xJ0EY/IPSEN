package server.sources.models.stories;

import java.util.ArrayList;

public class Story {

    private String story;
    private ArrayList<Choice> choices;

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
}
