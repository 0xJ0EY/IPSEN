package server.sources.notifications;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.models.Harvest;

import java.rmi.RemoteException;

public class ShowHarvestNotification implements NotificationInterface {

    private Harvest harvest;

    public ShowHarvestNotification(Harvest harvest) {
        this.harvest = harvest;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showHarvestSelection(this.harvest);
    }

}
