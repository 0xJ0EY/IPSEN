package client.source.components.villager;

import client.source.controllers.VillagerSelectionController;
import javafx.fxml.FXML;

import java.util.ArrayList;

/**
 * This class allows to create single selectable villager components
 * @author Joey de Ruiter
 */
public class SingleSelectableVillagerComponent extends SelectableVillagerComponent {

    private VillagerSelectionController controller;

    /**
     * For setting a controller
     * @param controller VillagerSelectionController
     * @author Joey de Ruiter
     */
    public void setController(VillagerSelectionController controller) {
        this.controller = controller;
    }

    /**
     * For selecting villagers
     * @author Joey de Ruiter
     */
    @FXML
    public void onClickSelect() {

        // Deselect all other villagers
        this.deselectVillagers();

        // Select this villager
        this.toggleSelected();

    }

    private void deselectVillagers() {
        ArrayList<SelectableVillagerComponent> components = controller.getSelectedVillagerComponents();

        if (components == null || components.size() == 0) return;

        for (SelectableVillagerComponent component : components) {
            component.deselect();
        }
    }

}
