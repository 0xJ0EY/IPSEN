package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.*;

/**
 * This class is a perk that can be given to buildings.
 * It can be used for the harvest action.
 *
 * @author Jan Douwe Sminia
 */
public class HarvestableGoodPerk implements Perk, Harvestable {
    private Good good;
    private int amountLeft = 2;

    /**
     * @param good
     */
    public HarvestableGoodPerk(Good good){
        this.good = good;
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

    /**
     * @return
     */
    public Good getGood() {
        return this.good;
    }

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

    }
}
