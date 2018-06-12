package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.ReputationRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * This class creates a ReputationReward Reward.
 * @author Richard Kerkvliet
 */
public class ReputationReward extends Reward {
    private int amount;

    /**
     * creates the ReputationReward.
     * @author Richard Kerkvliet
     */
    public ReputationReward(int amount) {
        this.amount = amount;
    }

    /**
     * Overrides the execute of Reward.
     * adds a Reputation to the PlayerBoard.
     * @param client
     * @throws RemoteException java.rmi.RemoteException
     * @throws ParserConfigurationException
     * @author Richard Kerkvliet
     */
    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        client.getGameClient().getPlayer().changeReputation(this.amount);
    }

    /**
     * returns a new ReputationRewardComponent.
     * @return ReputationRewardComponent RewardComponent
     * @author Richard Kerkvliet
     */
    @Override
    public RewardComponent getRewardComponent() {
        return new ReputationRewardComponent();
    }
}
