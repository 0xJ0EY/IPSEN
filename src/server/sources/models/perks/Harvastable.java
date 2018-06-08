package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.Good;

public interface Harvastable {
    public int amountLeft();
    public AnchorPane getGoodComponent();
    public Good harvestGood();
    public Harvastable getHarvestable();
}
