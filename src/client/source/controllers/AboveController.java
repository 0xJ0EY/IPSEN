package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import server.sources.interfaces.PlayerBoardControllerInterface;

import java.rmi.RemoteException;

public class AboveController implements Observable {


    @FXML Label labelBeds;
    @FXML Label labelCiders;
    @FXML Label labelPotions;

    private PlayerBoardControllerInterface playerBoard;

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
        playerBoard = this.client.playerBoardObserver.getState();

        this.updateBeds();
        this.updateCiders();
        this.updatePotions();
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
}
