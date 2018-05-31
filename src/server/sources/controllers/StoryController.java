package server.sources.controllers;

import server.sources.interfaces.StoryControllerInterface;
import server.sources.models.stories.Story;
import server.sources.models.stories.StoryFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StoryController extends UnicastRemoteObject implements StoryControllerInterface {

    private StoryFactory storyFactory = new StoryFactory();
    private ArrayList<Story> stories;

    public StoryController() throws RemoteException {
    }

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
