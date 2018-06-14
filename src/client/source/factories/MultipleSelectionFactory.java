package client.source.factories;

import client.source.components.villager.MultipleSelectableVillagerComponent;
import client.source.components.villager.SelectableVillagerComponent;

/**
 * A Class that allows to create a factory for creating multiple selectable villagers.
 * Created by Joey de Ruiter
 */
public class MultipleSelectionFactory extends VillagerSelectionComponentFactory {

    /**
     * A implemented method from a superclass that returns selectable villager components.
     * @return selectable villager components
     * @author Joey de Ruiter
     */
    @Override
    public SelectableVillagerComponent createComponent() {
        SelectableVillagerComponent component = new MultipleSelectableVillagerComponent();
        component.setController(this.controller);

        return component;
    }

}
