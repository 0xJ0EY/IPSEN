package client.source.factories;

import client.source.components.villager.MultibleSelectableVillagerComponent;
import client.source.components.villager.SelectableVillagerComponent;


public class MultipleSelectionFactory extends VillagerSelectionComponentFactory {

    @Override
    public SelectableVillagerComponent createComponent() {
        return new MultibleSelectableVillagerComponent();
    }

}
