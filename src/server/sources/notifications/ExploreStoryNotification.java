package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.models.stories.Story;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ExploreStoryNotification implements NotificationInterface {

    private Story story;
    private GameClientInterface target;

    public ExploreStoryNotification(GameClientInterface target, Story story) {
        this.target = target;
        this.story = story;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {

        if (this.target.equals(gameClient)) {
            gameClient.getClient().getExplore().enableTurnButton();
            gameClient.getClient().getExplore().enableConfirmButton();
        } else {
            gameClient.getClient().getExplore().disableTurnButton();
            gameClient.getClient().getExplore().disableConfirmButton();
        }

        gameClient.getClient().showExplore(this.story);
    }
}
