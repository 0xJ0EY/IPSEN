package server.sources.models.goods;

import java.io.Serializable;

/**
 * Created by robin on 6-6-2018.
 */
public class CiderGood implements Good, Serializable {
    @Override
    public String isGood() {
        return "FISH";
    }
}
