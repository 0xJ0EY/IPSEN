package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.rmi.RemoteException;

/**
 * Created by robin on 7-6-2018.
 */
public class TrainController implements ControllerInterface {

    public static Client client;
    @FXML
    private Parent root;
    @FXML private Button refreshButton;

    /**
     * Here are all villagerscontainers declared to stock villagers cards in villager market.
     */
    @FXML private FlowPane villagersContainer;
    @FXML private FlowPane trainerVillagersContainer;
    @FXML private FlowPane builderVillagersContainer;
    @FXML private FlowPane allroundVillagersContainer;


    @Override
    public Parent show() {

        try {
            this.retrieveVillagers();
            this.retrieveTrainerVillagers();
            this.retrieveBuilderVillagers();
            this.retrieveAllroundVillagers();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return this.root;
    }


    private void retrieveVillagers() throws  RemoteException {

    }

    private void retrieveTrainerVillagers() throws RemoteException {

    }

    private void retrieveBuilderVillagers() throws RemoteException {

    }

    private void retrieveAllroundVillagers() throws RemoteException {

    }

    private void updateVillagersView() {
    }
    private void updateTrainerVillagersView() {
    }
    private void updateBuilderVillagersView() {
    }
    private void updateAllroundVillagersView() {
    }

    public static void setClient(Client c) {
        client = c;
    }

}
