package client.source.components.villager;

import javafx.fxml.FXML;

/**
 * A class that allows to create multiple selectable villager components
 * Created by Joey de Ruiter
 */
public class MultipleSelectableVillagerComponent extends SelectableVillagerComponent {

    @FXML
    public void onClickSelect() {
        if (!this.controller.hasTurn()) return;

        // Select this villager
        this.toggleSelected();
    }

}
