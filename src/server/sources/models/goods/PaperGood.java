package server.sources.models.goods;

import java.io.Serializable;

public class PaperGood implements Good {

    @Override
    public String getBackground() {
        return "paper.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof PaperGood;
    }
}
