package server.sources.models.goods;

import java.io.Serializable;

public class FruitGood  implements Good, Serializable {

    @Override
    public String isGood() {
        return "FRUIT";
    }

    @Override
    public String getGood() {
        return "GREEN";
    }
}
