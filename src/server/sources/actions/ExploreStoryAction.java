package server.sources.actions;

import client.source.controllers.villager.VillagerSelectionController;
import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.stories.Story;
import server.sources.models.villagers.Villager;
import server.sources.notifications.ExploreStoryNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ExploreStoryAction implements VillagerActionInterface {

    private Story story;
    private GameClientInterface target;
    private ArrayList<Villager> selectedVillagers;

    public ExploreStoryAction(GameClientInterface target) {
        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {

        this.story = server.getGameController().getStories().randomStory();
        this.story.setVillagers(selectedVillagers);

        System.out.println("Generated new story");
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new ExploreStoryNotification(this.target, this.story);
    }

    @Override
    public void setSelectedVillagers(ArrayList<Villager> villagers) {
        this.selectedVillagers = villagers;
    }

}
