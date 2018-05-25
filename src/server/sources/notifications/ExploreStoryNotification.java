package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.models.stories.Story;

import java.rmi.RemoteException;

public class ExploreStoryNotification implements NotificationInterface {

    private Story story;

    public ExploreStoryNotification(Story story) {
        this.story = story;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        System.out.println("Dit is een explore notification");



        gameClient.getClient().showExplore(this.story);
    }
}
