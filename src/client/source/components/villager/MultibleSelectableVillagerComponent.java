package client.source.components.villager;

import javafx.fxml.FXML;

public class MultibleSelectableVillagerComponent extends SelectableVillagerComponent {

    @FXML
    public void onClickSelect() {
        // Select this villager
        this.toggleSelected();
    }

}
