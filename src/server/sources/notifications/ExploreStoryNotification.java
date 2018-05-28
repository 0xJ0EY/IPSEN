package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.models.stories.Story;

import java.rmi.RemoteException;

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
            gameClient.getClient().explore.enableTurnButton();
            gameClient.getClient().explore.enableConfirmButton();
        } else {
            gameClient.getClient().explore.disableTurnButton();
            gameClient.getClient().explore.disableConfirmButton();
        }

        gameClient.getClient().showExplore(this.story);
    }
}
