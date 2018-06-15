package server.sources.models.goods;

import java.io.Serializable;

public class RopeGood implements Good {

    @Override
    public String getBackground() {
        return "rope_good.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof RopeGood;
    }

    @Override
    public Good harvestGood() {
        return new RopeGood();
    }
}
