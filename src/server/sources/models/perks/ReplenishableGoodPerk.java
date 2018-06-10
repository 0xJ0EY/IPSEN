package server.sources.models.perks;

import server.sources.models.goods.*;

public class ReplenishableGoodPerk implements Perk, Harvestable, Replenishable {
    private Good good;

    public ReplenishableGoodPerk(Good good){
        this.good = good;
    }

    @Override
    public String getBackground() {
        return "../goods/" + good.getBackground();
    }

    @Override
    public void refresh() {

    }
}
