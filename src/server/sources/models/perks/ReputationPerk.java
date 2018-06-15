package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class ReputationPerk implements Perk, ActivateOnObtainedInterface {
    private final int REPUTATION = 1;
    private GameClientInterface gameClient;

    @Override
    public String getBackground() {
        return "reputation_perk.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public void activateOnObtainedPerk() throws RemoteException {
        gameClient.getPlayer().changeReputation(this.REPUTATION);

    }
}
