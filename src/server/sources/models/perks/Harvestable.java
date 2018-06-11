package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.Good;

public interface Harvestable extends Perk {

    public boolean canHarvest();

    public void harvest();

}
