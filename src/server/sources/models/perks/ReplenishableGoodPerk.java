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
        System.out.println("Refresh");
        this.value = this.original_value;
    }

    @Override
    public boolean canHarvest(){
        System.out.println("checken of het kan harvesten.");
        return this.value > 0;
    }

    @Override
    public int getAmount() {
        return this.value;
    }

    @Override
    public void harvest(){
        this.value--;
        System.out.println("Good harvested");
        System.out.println(value);

    }
}
