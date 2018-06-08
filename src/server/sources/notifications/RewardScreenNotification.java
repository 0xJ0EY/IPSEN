package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.models.stories.Option;

import java.rmi.RemoteException;

public class RewardScreenNotification implements NotificationInterface {

    private Option option;

    public RewardScreenNotification(Option option){
        this.option = option;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showRewards(option);
    }
}
