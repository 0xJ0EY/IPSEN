package server.sources.models.stories;

import java.util.ArrayList;

public class StoryController {

    private StoryFactory storyFactory = new StoryFactory();
    private ArrayList<Story> stories;

    public void load() {
        this.stories = storyFactory.loadStoriesFromXML();
    }

    public Story randomStory() {
        int index = (int) Math.round(Math.random() * stories.size());

        Story story = stories.get(index);
        stories.remove(story);

        return story;
    }

    public Story storyAt(int index) {
        return stories.get(index);
    }

}
