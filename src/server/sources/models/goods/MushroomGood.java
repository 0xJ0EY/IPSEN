package server.sources.models.goods;

import java.io.Serializable;

public class MushroomGood implements Good {

    public String getBackground() {
        return "mushroom_good.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof MushroomGood;
    }

    @Override
    public Good harvestGood() {
        return new MushroomGood();
    }
}
