package server.sources.notifications;

import server.sources.interfaces.BuildingInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class ShowBuildRewardNotification implements NotificationInterface {

    BuildingInterface building;

    public ShowBuildRewardNotification(BuildingInterface building) {
        this.building = building;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showBuildReward(this.building);
    }

}
