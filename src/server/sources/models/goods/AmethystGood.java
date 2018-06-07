package server.sources.models.goods;

import java.io.Serializable;

public class AmethystGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "AMETHYST";
    }
}
