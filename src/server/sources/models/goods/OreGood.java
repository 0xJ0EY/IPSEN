package server.sources.models.goods;

import java.io.Serializable;

public class OreGood implements Good {

    @Override
    public String getBackground() {
        return "ore.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof OreGood;
    }
}
