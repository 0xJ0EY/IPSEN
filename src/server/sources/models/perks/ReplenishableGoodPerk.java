package server.sources.models.perks;

import server.sources.models.goods.*;

public class ReplenishableGoodPerk implements Perk, Harvestable, Replenishable {

    private int original_value;

    private Good good;

    public ReplenishableGoodPerk(Good good){
        this.good = good;
    }

    @Override
    public String getBackground() {
        return "../good/" + good.getBackground();
    }

    public Good getGood() {
        return good;
    }

    @Override
    public void refresh() {

    }

    @Override
    public boolean canHarvest() {
        return false;
    }

    @Override
    public void harvest() {

    }
}
