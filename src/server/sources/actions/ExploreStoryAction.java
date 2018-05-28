package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.models.stories.Story;
import server.sources.models.stories.StoryFactory;
import server.sources.notifications.ExploreStoryNotification;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class ExploreStoryAction implements ActionInterface {

    private Story story;
    private GameClientInterface target;

    public ExploreStoryAction(GameClientInterface target) {
        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {

        try {
            this.story = new StoryFactory().randomStory();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        System.out.println("Generated new story");

    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new ExploreStoryNotification(this.target, this.story);
    }

}
