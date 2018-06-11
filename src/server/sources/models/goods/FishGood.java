package server.sources.models.goods;

import java.io.Serializable;

public class FishGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "FISH";
    }

    @Override
    public String getGood() {
        return "BLUE";
    }
}
