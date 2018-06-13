package server.sources.models.perks;

import server.sources.models.goods.*;

public class ReplenishableGoodPerk implements Perk, Harvestable, Refreshable {

    private int original_value = 2;
    private int value = 0;

    private Good good;

    public ReplenishableGoodPerk(Good good){
        this.good = good;
    }

    public String getBackground() {
        return "../good/" + good.getBackground();
    }

    public Good getGood() {
        return good;
    }

    @Override
    public void refresh() {
        this.value = this.original_value;
    }

    public boolean canHarvest(){
        return this.value > 0;
    }

    public void harvest(){
        this.value--;

    }
}
