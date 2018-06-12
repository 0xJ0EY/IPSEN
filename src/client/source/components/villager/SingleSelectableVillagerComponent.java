package client.source.components.villager;

import client.source.controllers.VillagerSelectionController;
import javafx.fxml.FXML;

import java.util.ArrayList;

/**
 * This class allows to create single selectable villager components
 * @author Richard Kerkvliet
 */
public class SingleSelectableVillagerComponent extends SelectableVillagerComponent {

    private VillagerSelectionController controller;

    /**
     * For setting a controller
     * @param controller
     * @author Richard Kerkvliet
     */
    public void setController(VillagerSelectionController controller) {
        this.controller = controller;
    }

    /**
     * For selecting villagers
     * @author Richard Kerkvliet
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
