package server.sources.models.goods;

import java.io.Serializable;

public class MushroomGood implements Good {

    public String getBackground() {
        return "mushroom.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof MushroomGood;
    }
}
