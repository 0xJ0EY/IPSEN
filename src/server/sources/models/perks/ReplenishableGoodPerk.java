package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.*;

public class ReplenishableGoodPerk implements Perk, Harvastable {
    private Good good;
    private int amountLeft = 1;

    public ReplenishableGoodPerk(Good good){
        this.good = good;
    }

    /**
     * This is only for setting information stats on building card.
     * @return Informatie van statistieken over dat building card
     * @author Robin Silvério
     */
    @Override
    public String toString() {
        return "Replenishable: " + this.good.toString();
    }

    public Harvastable getHarvestable(){
        return this;
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

    public void replenishGood(){
        amountLeft = 0;
    }
}
