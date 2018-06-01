package client.source.controllers.villager;

import client.source.Client;
import client.source.components.villager.VillagerComponent;
import client.source.controllers.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VillagerSelectionController implements ControllerInterface {

    @FXML private Parent root;

    @FXML private FlowPane villagerContainer;

    private VillagerActionInterface action;

    private ArrayList<Villager> villagers;

    private ArrayList<VillagerComponent> villagerComponents;

    private Client client;

    public Parent show() {

        try {
            this.retrieveVillagers();


            this.updateVillagersView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return this.root;
    }


    public void retrieveVillagers() throws RemoteException {
        this.villagers = client.getGameClient().getPlayer().getPlayerBoard().listAvailableVillagers();
    }

    private void updateVillagersView() {

        this.villagerComponents = new ArrayList<VillagerComponent>();
        this.villagerContainer.getChildren().clear();

        System.out.println(this.villagers);

        for (Villager villager : this.villagers) {

            VillagerComponent villagerComponent = new VillagerComponent(villager);
            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    @FXML
    private void onClickSelect() {
        try {
            ArrayList<Villager> selected = this.getSelectedVillagers();

            this.action.setSelectedVillagers(selected);
            this.client.getGameClient().requestAction(this.action);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Villager> getSelectedVillagers() {
        ArrayList<Villager> selected = new ArrayList<Villager>();

        for (VillagerComponent villagerComponent : this.villagerComponents) {

            if (villagerComponent.isSelected()) {
                selected.add(villagerComponent.getVillager());
            }
            
        }

        return selected;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVillagerAction(VillagerActionInterface action) {
        this.action = action;
    }
}
