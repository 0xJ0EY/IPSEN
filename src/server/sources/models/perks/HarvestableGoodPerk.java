package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;
import server.sources.models.goods.*;

import java.rmi.RemoteException;

/**
 * This class is a perk that can be given to buildings.
 * It can be used for the harvest action.
 *
 * @author Jan Douwe Sminia
 */
public class HarvestableGoodPerk implements Harvestable {
    private Good good;
    private int amountLeft;

    /**
     * @param good
     */
    public HarvestableGoodPerk(Good good){
        this.good = good;
        this.amountLeft = 2;
    }

    /**
     * sets the right background for the good.
     *
     * @return
     */
    @Override
    public String getBackground() {
        return "../good/" + good.getBackground();
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {

    }

    /**
     * @return
     */
    public Good getGood() {
        return this.good;
    }

    @Override
    /**
     * This check if you still can harvest the good.
     *
     * @return
     */
    public boolean canHarvest() {
        return this.amountLeft > 0;

    }

    /**
     * This counts as giving a good to the player who harvests it.
     */
    @Override
    public void harvest() {
        this.amountLeft--;
        System.out.println("Good harvested");

    }
}
