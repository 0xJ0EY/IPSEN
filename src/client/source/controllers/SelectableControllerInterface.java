package client.source.controllers;

import client.source.components.building.SelectableBuildingComponent;

import java.util.ArrayList;

public interface SelectableControllerInterface extends ControllerInterface {

    public ArrayList<SelectableBuildingComponent> getSelectedBuildingComponents();

}
