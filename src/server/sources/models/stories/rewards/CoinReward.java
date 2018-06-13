package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.CoinRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * This class creates a CoinReward Reward.
 * @author Richard Kerkvliet
 */
public class CoinReward extends Reward {
    private int amount;

    /**
     * creates the CoinReward.
     * @param amount
     * @author Richard Kerkvliet
     */
    public CoinReward(int amount){
        this.amount = amount;
    }

    /**
     * Overrides the execute of Reward.
     * adds Coins to the PlayerBoard.
     * @param client
     * @throws RemoteException java.rmi.RemoteException
     * @throws ParserConfigurationException
     * @author Richard Kerkvliet
     */
    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        this.playerBoard.addCoins(this.amount);
    }

    /**
     * returns a new CoinRewardComponent.
     * @return CoinRewardComponent RewardComponent
     * @author Richard Kerkvliet
     */
    @Override
    public RewardComponent getRewardComponent() {
        return new CoinRewardComponent();
    }
}
