package client.source.factories;

import client.source.components.villager.MultibleSelectableVillagerComponent;
import client.source.components.villager.SelectableVillagerComponent;

/**
 * A Class that allows to create a factory for creating multiple selectable villagers.
 * Created by Joey de Ruiter
 */
public class MultipleSelectionFactory extends VillagerSelectionComponentFactory {

    @Override
    public SelectableVillagerComponent createComponent() {
        return new MultibleSelectableVillagerComponent();
    }

}
