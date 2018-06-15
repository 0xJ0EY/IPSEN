package client.source.components.building;

import java.util.ArrayList;

/**
 * A class that allows the user only to select one buildingcomponent.
 * Created by Joey de Ruiter
 */
public class SingleSelectableBuildingComponent extends SelectableBuildingComponent {
    /**
     * In contrast of multiple selection buildings, in some action, it is only allowed to choose maximum one building.
     * @author Joey de Ruiter
     */
    @Override
    public void onClickCard() {
        if (!this.controller.hasTurn()) return;

        this.deselectBuildings();

        this.toggleSelected();
    }

    /**
     * Deselect all buildingcomponents
     * @author Joey de Ruiter
     */
    private void deselectBuildings() {
        ArrayList<SelectableBuildingComponent> components = this.controller.getSelectedBuildingComponents();

        if (components == null || components.size() == 0) return;

        for (SelectableBuildingComponent component : components) {
            component.deselect();
        }
    }

}
