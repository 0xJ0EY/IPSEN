package server.sources.models.perks;

import server.sources.models.goods.*;

public class HarvestableGoodPerk implements Perk, Harvestable {
    private Good good;
    private int amountLeft;

    public HarvestableGoodPerk(Good good){
        this.good = good;
        this.amountLeft = 2;
    }

    @Override
    public String getBackground() {
        return "../good/" + good.getBackground();
    }

    public Good getGood() {
        return this.good;
    }

    @Override
    public boolean canHarvest() {
        return this.amountLeft > 0;

    }

    @Override
    public void harvest() {
        this.amountLeft--;
        System.out.println("Good harvested");

    }
}
