package client.source.factories;

import client.source.components.villager.SelectableVillagerComponent;
import client.source.components.villager.SingleSelectableVillagerComponent;

/**
 * A Class that allows to create a factory for creating single selectable villagers.
 * Created by Joey de Ruiter
 */
public class SingleSelectionFactory extends VillagerSelectionComponentFactory {

    @Override
    public SelectableVillagerComponent createComponent() {
        SingleSelectableVillagerComponent component = new SingleSelectableVillagerComponent();
        component.setController(this.controller);

        return component;
    }
}
