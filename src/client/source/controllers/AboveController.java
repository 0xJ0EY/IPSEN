package client.source.controllers;

import client.source.Client;
import client.source.components.villager.VillagerComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AboveController implements Observable {

    @FXML Label labelBeds;
    @FXML Label labelCiders;
    @FXML Label labelPotions;

    @FXML FlowPane activeVillagers;
    @FXML FlowPane injuredVillagers;
    @FXML FlowPane tiredVillagers;

    private PlayerBoardInterface playerBoard;

    private Client client;

    public AboveController() throws RemoteException {
    }

    /**
     * For setting a client
     * @param client
     */
    public void registerClient(Client client) {
        this.client = client;
        this.client.playerBoardObserver.attach(this);
    }

    @Override
    public void updateObserver() {
        this.playerBoard = this.client.playerBoardObserver.getState();

        this.updateBeds();
        this.updateCiders();
        this.updatePotions();
        this.updateVillagers();
    }

    private void updateBeds() {
        try {
            labelBeds.setText(String.format("Beds: %s", playerBoard.getBeds()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updateCiders() {
        try {
            labelCiders.setText(String.format("Ciders: %s", playerBoard.getCiders()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updatePotions() {
        try {
            labelPotions.setText(String.format("Ciders: %s", playerBoard.getPotions()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updateVillagers() {

        this.activeVillagers.getChildren().clear();
        this.injuredVillagers.getChildren().clear();
        this.tiredVillagers.getChildren().clear();

        try {
            ArrayList<VillagerInterface> villagers = playerBoard.listVillagers();

            for (VillagerInterface villager : villagers) {

                VillagerComponent villagerComponent = new VillagerComponent();
                villagerComponent.setModel(villager);
                villagerComponent.load();

                switch (villager.getState()) {
                    case USABLE:
                        this.activeVillagers.getChildren().add(villagerComponent);
                        break;

                    case TIRED:
                        this.injuredVillagers.getChildren().add(villagerComponent);
                        break;

                    case INJURED:
                        this.tiredVillagers.getChildren().add(villagerComponent);
                        break;
                }
            }

            System.out.println(villagers);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
