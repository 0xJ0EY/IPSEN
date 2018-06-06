package server.sources.models.goods;

import java.io.Serializable;

public class MushroomGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "MUSHROOM";
    }
}
