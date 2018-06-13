package server.sources.models.perks;

import server.sources.models.goods.*;

/**
 * This class is a perk that can be given to a building.
 * It is used when harvesting.
 *
 * @author Jan Douwe Sminia
 */
public class ReplenishableGoodPerk implements Harvestable, Refreshable {

    private int original_value = 2;
    private int value = 0;

    private Good good;

    /**
     * @param good
     */
    public ReplenishableGoodPerk(Good good){
        this.good = good;
    }

    /**
     * Gets the background of the right perk.
     * @return
     */
    public String getBackground() {
        return "../good/" + good.getBackground();
    }

    /**
     * @return
     */
    public Good getGood() {
        return good;
    }

    /**
     * This method is called at the start of every round.
     * It gives the perk een extra good.
     */
    @Override
    public void refresh() {
        this.value = this.original_value;
    }

    /**
     * Checks if there are goods left.
     *
     * @return
     */
    public boolean canHarvest(){
        return this.value > 0;
    }

    /**
     * Removes one good.
     */
    public void harvest(){
        this.value--;

    }
}
