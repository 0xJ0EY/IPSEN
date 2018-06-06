package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.*;

public class HarvestableGoodPerk implements Perk, Harvastable{
    private Good good;

    private int goodsLeft;

    public HarvestableGoodPerk(String good){
        this.goodsLeft = 2;

        switch (good.toUpperCase()){
            case "MUSHROOM" : this.good = new MushroomGood();
                break;
            case "FISH" : this.good = new FishGood();
                break;
            case "FRUIT" : this.good = new FruitGood();
                break;
            case "PAPER" : this.good = new PaperGood();
                break;
            case "POT" : this.good = new PotGood();
                break;
            case "ROPE" : this.good = new MushroomGood();
                break;
            case "ORE" : this.good = new MushroomGood();
                break;
            case "AMETHYST" : this.good = new MushroomGood();
                break;
        }

    }

    @Override
    public boolean canHarvest() {
        if (this.goodsLeft > 0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public AnchorPane getGoodComponent() {
        return good.getGood();
    }

    @Override
    public Good Harvest() {
        return this.good;
    }

    public int getGoodsLeft(){
        return this.goodsLeft;
    }
}
