package client.source.components.building;

import java.util.ArrayList;

public class SingleSelectableBuildingComponent extends SelectableBuildingComponent {

    @Override
    public void onClickCard() {
        this.deselectBuildings();

        this.toggleSelected();
    }

    private void deselectBuildings() {
        ArrayList<SelectableBuildingComponent> components = this.controller.getSelectedBuildingComponents();

        if (components == null || components.size() == 0) return;

        for (SelectableBuildingComponent component : components) {
            component.deselect();
        }
    }

}
