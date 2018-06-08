package client.source.controllers;

import client.source.Client;
import client.source.components.villager.RestVillagerComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.actions.RestVillagerAction;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VillagerRestController implements ControllerInterface {

    @FXML Parent root;

    @FXML private FlowPane villagerContainer;

    private Client client;

    private PlayerBoardInterface playerBoard;
    private ArrayList<VillagerInterface> villagers;

    private ArrayList<RestVillagerComponent> villagerComponents;

    @Override
    public Parent show() {

        this.fetchVillagers();

        this.load();

        return this.root;
    }

    public void fetchVillagers() {
        try {
            this.villagers = this.playerBoard.listVillagers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void load() {
        this.villagerComponents = new ArrayList<RestVillagerComponent>();
        villagerContainer.getChildren().clear();

        for (VillagerInterface villager : this.villagers) {
            RestVillagerComponent villagerComponent = new RestVillagerComponent();

            villagerComponent.setPlayerBoard(this.playerBoard);
            villagerComponent.setModel(villager);
            villagerComponent.setController(this);
            villagerComponent.load();

            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }

        this.update();
    }

    public void update() {

        try {
            boolean hasCider = this.playerBoard.hasCider();
            boolean hasPotion = this.playerBoard.hasPotion();
            boolean hasBeds = this.playerBoard.hasBeds();

            for (RestVillagerComponent villagerComponent : villagerComponents) {
                villagerComponent.update(hasCider, hasPotion, hasBeds);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
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

    @FXML private void onClickSelect() throws RemoteException {

        this.client.getGameClient().getPlayer().doAction(new RestVillagerAction());
        this.client.showMain();

    }
}
