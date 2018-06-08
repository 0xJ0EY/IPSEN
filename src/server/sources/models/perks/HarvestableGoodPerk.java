package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.*;

public class HarvestableGoodPerk implements Perk, Harvastable {
    private Good good;
    private int amountLeft = 2;

    public HarvestableGoodPerk(Good good){
        this.good = good;
    }

    /**
     * This is only for setting information stats on building card.
     * @return Informatie van statistieken over dat building card
     * @author Robin Silvério
     */
    @Override
    public String toString() {
        return "Harvestable: " + this.good.isGood();
    }

    @Override
    public int amountLeft() {
        return amountLeft;
    }

    @Override
    public AnchorPane getGoodComponent() {
        return good.getGood();
    }

    @Override
    public Good harvestGood() {
        amountLeft--;
        return good;
    }

    @Override
    public Harvastable getHarvestable() {
        return this;
    }

}
