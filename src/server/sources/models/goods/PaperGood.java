package server.sources.models.goods;

import java.io.Serializable;

public class PaperGood implements Good, Serializable {

    @Override
    public String isGood() {
        return "PAPER";
    }

    @Override
    public String getGood() {
        return "WHITE";
    }
}
