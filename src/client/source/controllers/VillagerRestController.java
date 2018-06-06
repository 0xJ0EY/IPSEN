package client.source.controllers;

import client.source.Client;
import client.source.components.villager.RestVillagerComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VillagerRestController implements ControllerInterface {

    @FXML Parent root;

    @FXML private FlowPane villagerContainer;

    private Client client;

    private PlayerBoardControllerInterface playerBoard;
    private ArrayList<VillagerInterface> villagers;

    private ArrayList<RestVillagerComponent> villagerComponents;

    @Override
    public Parent show() {

        this.fetchVillagers();

        this.updateView();

        return this.root;
    }

    public void fetchVillagers() {
        try {
            this.villagers = this.playerBoard.listVillagers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void updateView() {
        this.villagerComponents = new ArrayList<RestVillagerComponent>();
        villagerContainer.getChildren().clear();

        for (VillagerInterface villager : this.villagers) {
            RestVillagerComponent villagerComponent = new RestVillagerComponent();

            villagerComponent.setPlayerBoard(this.playerBoard);
            villagerComponent.setModel(villager);
            villagerComponent.load();

            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    public void setClient(Client client) {
        try {
            this.client = client;
            this.playerBoard = client.getGameClient().getPlayer().getPlayerBoard();

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    @FXML private void onClickSelect() {

    }
}
