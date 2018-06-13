package server.sources.models.goods;

import java.io.Serializable;

public class AmethystGood implements Good {

    @Override
    public String getBackground() {
        return "amethyst.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof AmethystGood;
    }

    @Override
    public Good harvestGood() {
        return new AmethystGood();
    }
}
