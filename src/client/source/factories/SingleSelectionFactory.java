package client.source.factories;

import client.source.components.villager.SelectableVillagerComponent;
import client.source.components.villager.SingleSelectableVillagerComponent;

public class SingleSelectionFactory extends VillagerSelectionComponentFactory {

    @Override
    public SelectableVillagerComponent createComponent() {
        SingleSelectableVillagerComponent component = new SingleSelectableVillagerComponent();
        component.setController(this.controller);

        return component;
    }
}
