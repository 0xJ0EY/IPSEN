package server.sources.models.goods;

import java.io.Serializable;

/**
 * This class is an interface for goods.
 *
 * @author Jan Douwe Sminia
 */
public interface Good extends Serializable {

    /**
     * Gets the background of the good.
     *
     * @return
     */
    public String getBackground();

    /**
     * it checks if the good is an instance of the interface good.
     *
     * @param good
     * @return
     */
    public boolean sameInstance(Good good);

    public Good harvestGood();

}
