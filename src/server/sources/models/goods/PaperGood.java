package server.sources.models.goods;

import java.io.Serializable;

public class PaperGood implements Good {

    @Override
    public String getBackground() {
        return "paper_good.png";
    }

    public boolean sameInstance(Good good) {
        return good instanceof PaperGood;
    }

    @Override
    public Good harvestGood() {
        return new PaperGood();
    }
}
