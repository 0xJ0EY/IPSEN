package client.source.controllers;

import client.source.Client;
import client.source.components.villager.SelectableVillagerComponent;

import java.util.ArrayList;

public interface VillagerSelectionControllerInterface extends ControllerInterface {

    public boolean hasTurn();

    public ArrayList<SelectableVillagerComponent> getSelectedVillagerComponents();

    public void showMessage(String message);

    public void update();

    public Client getClient();

}
