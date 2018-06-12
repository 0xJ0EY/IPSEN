package server.sources.models.stories.rewards;

import client.source.Client;
import client.source.components.reward.GoodRewardComponent;
import client.source.components.reward.RewardComponent;
import server.sources.models.goods.Good;
import server.sources.models.stories.Reward;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;

/**
 * This class creates a GoodReward Reward.
 * @author Richard Kerkvliet
 */
public class GoodReward extends Reward {
    private Good good;
    private int value;

    /**
     * creates the GoodReward.
     * @param good
     * @param value
     * @author Richard Kerkvliet
     */
    public GoodReward(Good good, int value){
        this.good = good;
        this.value = value;
    }

    /**
     * Overrides the execute of Reward.
     * adds a Good to the PlayerBoard.
     * @param client
     * @throws RemoteException
     * @throws ParserConfigurationException
     * @author Richard Kerkvliet
     */
    @Override
    public void execute(Client client) throws RemoteException, ParserConfigurationException {
        super.execute(client);
        for(int i=0; i<value; i++) {

            playerBoard.addGood(good);
        }
        System.out.println("goods: "+ playerBoard.getGoods());
    }

    /**
     * returns the type of the Good.
     * @return Good good
     * @author Richard Kerkvliet
     */
    public Good isGood(){
        return good;
    }

    /**
     * returns a new GoodRewardComponent.
     * @return GoodRewardComponent RewardComponent
     * @author Richard Kerkvliet
     */
    @Override
    public RewardComponent getRewardComponent() {
        return new GoodRewardComponent();
    }
}
