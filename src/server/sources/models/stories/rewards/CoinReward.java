package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.CoinRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class CoinReward extends Reward {
    private int amount;

    public CoinReward(int amount){
        this.amount = amount;
    }

    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        this.playerBoard.addCoins(this.amount);
    }

    @Override
    public RewardComponent getRewardComponent() {
        return new CoinRewardComponent();
    }
}
