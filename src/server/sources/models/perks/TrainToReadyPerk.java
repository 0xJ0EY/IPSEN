package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class TrainToReadyPerk implements Perk {
    private String perk = "Train to ready";

    @Override
    public String getBackground() {
        return "train_to_ready.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        gameClient.getPlayer().getPlayerBoard().obtainedTrainToReadyPerk();
    }
}
