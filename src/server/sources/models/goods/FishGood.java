package server.sources.models.goods;

import java.io.Serializable;

public class FishGood implements Good {

    @Override
    public String getBackground() {
        return "fish.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof FishGood;
    }

    @Override
    public Good harvestGood() {
        return new FishGood();
    }
}
