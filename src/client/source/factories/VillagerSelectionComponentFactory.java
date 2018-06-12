package client.source.factories;

import client.source.components.villager.SelectableVillagerComponent;
import client.source.controllers.VillagerSelectionController;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

/**
 * A Class that allows to create a factory for creating selectable villager components.
 * Created by Joey de Ruiter
 */
public abstract class VillagerSelectionComponentFactory {

    protected VillagerSelectionController controller;

    public void setController(VillagerSelectionController controller) {
        this.controller = controller;
    }

    public abstract SelectableVillagerComponent createComponent();

}
