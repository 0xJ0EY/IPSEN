package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.PotionRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * This class creates a PotionReward Reward.
 * @author Richard Kerkvliet
 */
public class PotionReward extends Reward {

    /**
     * creates the GoodReward.
     * @author Richard Kerkvliet
     */
    public PotionReward() {}

    /**
     * Overrides the execute of Reward.
     * adds a Potion to the PlayerBoard.
     * @param client
     * @throws RemoteException
     * @throws ParserConfigurationException
     * @author Richard Kerkvliet
     */
    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        playerBoard.addPotion();
    }

    /**
     * returns a new PotionRewardComponent.
     * @return PotionRewardComponent RewardComponent
     * @author Richard Kerkvliet
     */
    @Override
    public RewardComponent getRewardComponent() {
        return new PotionRewardComponent();
    }
}
