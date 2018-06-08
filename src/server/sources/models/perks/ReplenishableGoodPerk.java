package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.*;

public class ReplenishableGoodPerk implements Perk, Harvastable {
    private Good good;
    private int amountLeft = 1;

    public ReplenishableGoodPerk(String good){
        switch (good){
            case "AMETHYST":
                this.good = new AmethystGood();
                break;
            case "PAPER":
                this.good = new PaperGood();
                break;
            case "FISH":
                this.good = new FishGood();
                break;
            case "FRUIT":
                this.good = new FruitGood();
                break;
            case "MUSHROOM":
                this.good = new MushroomGood();
                break;
            case "ORE":
                this.good = new OreGood();
                break;
            case "POT":
                this.good = new PotGood();
                break;
            case "ROPE":
                this.good = new RopeGood();
                break;
            case "CIDER":
                this.good = new CiderGood();
                break;
            default:
                this.good = null;
                break;
        }
    }

    /**
     * This is only for setting information stats on building card.
     * @return Informatie van statistieken over dat building card
     * @author Robin Silvério
     */
    @Override
    public String toString() {
        return "Replenishable: " + this.good.isGood();
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

    public void ik(){
        int i = 0;
        i--;
    }

    public void replenishGood(){
        amountLeft = 0;
    }
}
