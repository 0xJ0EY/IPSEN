package server.sources.models.goods;

import java.io.Serializable;

public class PotGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "POT";
    }
}
