package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.Good;

/**
 * This class is an interface for every perk that can be used for harvest.
 *
 * @author Jan Douwe Sminia
 */
public interface Harvestable extends Perk {

    /**
     * Checks if it still has goods left.
     *
     * @return
     */
    public boolean canHarvest();

    public int getAmountLeft();

    /**
     * Removes one good.
     */
    public void harvest();

    public Good getGood();

}
