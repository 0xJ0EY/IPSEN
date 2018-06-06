package server.sources.models.perks;

import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.Good;

public interface Harvastable {
    public boolean canHarvest();
    public AnchorPane getGoodComponent();
    public Good Harvest();
}
