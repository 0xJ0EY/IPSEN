package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.RewardComponent;
import client.source.components.reward.VillagerRewardComponent;
import server.sources.models.stories.Reward;
import server.sources.models.villagers.VillagerFactory;
import server.sources.strategies.villagers.*;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

public class VillagerReward extends Reward {
    private String type;

    public VillagerReward(String type) {
        this.type = type;
    }

    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);

        this.playerBoard.executeVillagerStrategy(this.rewardVillager(type));
    }

    private AddVillagerStrategy rewardVillager(String type) {

        switch (type){
            case "TIN":
                return new AddTinVillagerStrategy();

            case "OIL":
                return new AddOilGirlVillagerStrategy();

            case "CAT":
                return new AddCatVillagerStrategy();

            case "FISH":
                return new AddFishVillagerStrategy();

            default:
                System.out.println("fout in stories.xml");
                return null;
        }

    }

    @Override
    public RewardComponent getRewardComponent() {
        return new VillagerRewardComponent();
    }
}