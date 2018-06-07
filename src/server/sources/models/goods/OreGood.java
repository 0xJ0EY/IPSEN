package server.sources.models.goods;

import java.io.Serializable;

public class OreGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "ORE";
    }
}
