package server.sources.models.goods;

import java.io.Serializable;

public class PotGood implements Good {

    @Override
    public String getBackground() {
        return "pot.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof PotGood;
    }

    @Override
    public Good harvestGood() {
        return new PotGood();
    }
}
