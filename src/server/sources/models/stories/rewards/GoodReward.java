package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.GoodRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class GoodReward extends Reward {
    private String type;
    private int value;

    public GoodReward(String type, int value){
        this.type = type;
        this.value = value;
    }

    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        for(int i=0; i<=value; i++) {
            playerBoard.addGood(type);
        }
    }

    @Override
    public RewardComponent getRewardComponent() {
        return new GoodRewardComponent();
    }
}
