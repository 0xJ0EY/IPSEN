package server.sources.models.perks;

import server.sources.models.goods.*;

public class ReplenishableGoodPerk implements Perk, Harvestable, Refreshable {

    private final int original_value = 1;
    private int value;

    private Good good;

    public ReplenishableGoodPerk(Good good){
        this.good = good;
        this.value = 1;
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

    @Override
    public boolean canHarvest(){
        return this.value > 0;
    }

    @Override
    public void harvest(){
        this.value--;
        System.out.println(value);

    }
}
