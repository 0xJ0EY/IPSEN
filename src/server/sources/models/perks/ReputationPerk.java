package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class ReputationPerk implements Perk {
    private final int REPUTATION = 1;

    @Override
    public String getBackground() {
        return "reputation_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        gameClient.getPlayer().changeReputation(this.REPUTATION);
        System.out.println("Reputation gained");
    }
}
