package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class TrainToReadyPerk implements Perk, ActivateOnObtainedInterface {
    private String perk = "Train to ready";
    private GameClientInterface gameClient;

    @Override
    public String getBackground() {
        return "train_to_ready.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public void activateOnObtainedPerk() throws RemoteException {
        gameClient.getPlayer().getPlayerBoard().obtainedTrainToReadyPerk();

    }
}
