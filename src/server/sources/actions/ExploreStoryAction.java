package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.models.stories.Story;
import server.sources.notifications.ExploreStoryNotification;

import java.rmi.RemoteException;

public class ExploreStoryAction implements ActionInterface {

    private Story story;
    private GameClientInterface target;

    public ExploreStoryAction(GameClientInterface target) {
        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {

        this.story = server.getGameController().getStories().randomStory();

        System.out.println("Generated new story");
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new ExploreStoryNotification(this.target, this.story);
    }

}
