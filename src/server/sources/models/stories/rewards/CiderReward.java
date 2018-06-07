package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.CiderRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class CiderReward extends Reward {

    public CiderReward() {}

    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        playerBoard.addCider();
    }

    @Override
    public RewardComponent getRewardComponent() {
        return new CiderRewardComponent();
    }
}
