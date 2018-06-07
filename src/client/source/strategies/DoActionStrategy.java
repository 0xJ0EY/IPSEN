package client.source.strategies;

import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

public class DoActionStrategy implements VillagerSelectionStrategy {

    @Override
    public void execute(GameClientInterface gameClient, ActionInterface request) {
        try {
            gameClient.getPlayer().doAction(request);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
