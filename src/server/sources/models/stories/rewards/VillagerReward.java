package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.RewardComponent;
import client.source.components.reward.VillagerRewardComponent;
import javafx.scene.layout.Region;
import server.sources.models.stories.Reward;
import server.sources.models.villagers.Villager;
import server.sources.models.villagers.VillagerFactory;
import server.sources.strategies.villagers.*;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * This class creates a VillagerReward Reward.
 * @author Richard Kerkvliet
 */
public class VillagerReward extends Reward {
    private String type;

    /**
     * creates the VillagerReward.
     * @param type String
     * @author Richard Kerkvliet
     */
    public VillagerReward(String type) {
        this.type = type;
    }

    /**
     * Overrides the execute of Reward.
     * adds a Villager to the PlayerBoard.
     * @param client
     * @throws RemoteException
     * @throws ParserConfigurationException
     * @author Richard Kerkvliet
     */
    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);

        this.playerBoard.executeVillagerStrategy(this.rewardVillager());
    }

    private AddVillagerStrategy rewardVillager() {

        switch (this.type){
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

    /**
     * returns the String of the location of the special villager background
     * @return String
     * @author Richard Kerkvliet
     */
    public String getVillagerBackground() {

        switch (this.type){
            case "TIN":
                return "tin_villager_background.png";

            case "OIL":
                return "oil_villager_background.png";

            case "CAT":
                return "cat_villager_background.png";

            case "FISH":
                return "fish_villager_background.png";

            default:
                System.out.println("fout in stories.xml");
                return null;
        }

    }

    /**
     * returns a new VillagerRewardComponent.
     * @return VillagerRewardComponent RewardComponent
     * @author Richard Kerkvliet
     */
    @Override
    public RewardComponent getRewardComponent() {
        return new VillagerRewardComponent();
    }
}
