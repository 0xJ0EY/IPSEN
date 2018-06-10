package server.sources.notifications;

import server.sources.actions.HarvestAction;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;

import java.rmi.RemoteException;

public class ShowHarvestNotification implements NotificationInterface {

    private HarvestAction harvest;

    public ShowHarvestNotification(HarvestAction harvest) {
        this.harvest = harvest;
    }

    @Override
    public void execute(GameClientInterface gameClient) throws RemoteException {
        gameClient.getClient().showHarvestSelection(this.harvest);
    }

}
