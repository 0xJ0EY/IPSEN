package server.sources.models.goods;

import java.io.Serializable;

public class RopeGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "ROPE";
    }

    @Override
    public String getGood() {
        return "GREY";
    }
}
