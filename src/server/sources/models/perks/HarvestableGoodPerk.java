package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.*;

public class HarvestableGoodPerk implements Perk, Harvestable {
    private Good good;
    private int amountLeft = 2;

    public HarvestableGoodPerk(Good good){
        this.good = good;
    }

    @Override
    public String getBackground() {
        return "../goods/" + good.getBackground();
    }
}
