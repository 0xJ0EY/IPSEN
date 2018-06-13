package server.sources.models.goods;

import java.io.Serializable;

public class FruitGood implements Good {

    @Override
    public String getBackground() {
        return "fruit.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof FruitGood;
    }
}
