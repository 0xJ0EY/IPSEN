package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.CiderRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * This class creates a CiderReward Reward.
 * @author Richard Kerkvliet
 */
public class CiderReward extends Reward {

    /**
     * creates the CiderReward.
     * @author Richard Kerkvliet
     */
    public CiderReward() {}

    /**
     * Overrides the execute of Reward.
     * adds a Cider to the PlayerBoard.
     * @param client
     * @throws RemoteException java.rmi.RemoteException
     * @throws ParserConfigurationException
     * @author Richard Kerkvliet
     */
    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        playerBoard.addCider();
    }

    /**
     * returns a new CiderRewardComponent.
     * @return CiderRewardComponent RewardComponent
     * @author Richard Kerkvliet
     */
    @Override
    public RewardComponent getRewardComponent() {
        return new CiderRewardComponent();
    }
}
