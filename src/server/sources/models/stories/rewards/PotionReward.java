package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.PotionRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class PotionReward extends Reward {

    public PotionReward() {

    }

    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        playerBoard.addPotion();
    }

    @Override
    public RewardComponent getRewardComponent() {
        return new PotionRewardComponent();
    }
}
