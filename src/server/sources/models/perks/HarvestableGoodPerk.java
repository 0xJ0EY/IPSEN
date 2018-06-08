package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.*;

public class HarvestableGoodPerk implements Perk, Harvastable {
    private Good good;
    private int amountLeft = 2;

    public HarvestableGoodPerk(String good){
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
