package server.sources.models.perks;

import server.sources.models.goods.Good;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForThingsPerk implements Perk {

    private final int value;
    private Good good;

    public VillagePointsForThingsPerk(int value, Good good) {
        this.value = value;
        this.good = good;
    }

    @Override
    public String getBackground() {
        return null;
    }
}
