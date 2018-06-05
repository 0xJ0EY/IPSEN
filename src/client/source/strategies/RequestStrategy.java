package client.source.strategies;

import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;


public class RequestStrategy implements VillagerSelectionStrategy {

    @Override
    public void execute(GameClientInterface gameClient, ActionInterface request) {
        try {
            gameClient.requestAction(request);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
