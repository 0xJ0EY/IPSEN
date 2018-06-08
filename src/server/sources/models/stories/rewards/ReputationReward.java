package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.ReputationRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class ReputationReward extends Reward {
    private int amount;

    public ReputationReward(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        client.getGameClient().getPlayer().changeReputation(this.amount);
    }

    @Override
    public RewardComponent getRewardComponent() {
        return new ReputationRewardComponent();
    }
}
