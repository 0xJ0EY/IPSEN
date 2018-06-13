package client.source.controllers;

import client.source.Client;
import client.source.factories.*;
import client.source.strategies.DoActionStrategy;
import client.source.strategies.RequestStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import server.sources.actions.*;
import server.sources.notifications.EndOfGameNotification;

import java.rmi.RemoteException;

/**
 * A class that acts as an intermediate between turnview and models
 * Created by Robin Silverio
 */
public class TurnController {

    private Client client;

    @FXML private Button exploreButton;
    @FXML private Button labourButton;
    @FXML private Button harvestButton;
    @FXML private Button trainButton;

    /**
     * This allows user to do the explore action
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @FXML private void explore() throws RemoteException {

        client.showVillagerSelection(
            new AllVillagerSelectionFactory(),
            new ExploreStoryAction(this.client.getGameClient()),
            new RequestStrategy(),
            new MultipleSelectionFactory()
        );

    }

    /**
     * This allows user to do the build action.
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @FXML private void build() throws RemoteException {


        client.showVillagerSelection(
            new BuilderVillagerSelectionFactory(),
            new BuildAction(this.client.getGameClient()),
            new RequestStrategy(),
            new SingleSelectionFactory()
        );

    }

    /**
     * This allows user to do labor action in order to get coins
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @FXML private void labor() throws RemoteException {

        client.showVillagerSelection(
            new AllVillagerSelectionFactory(),
            new LaborAction(this.client.getGameClient()),
            new DoActionStrategy(),
            new SingleSelectionFactory()
        );

    }

    /**
     * This allows user to do a harvest action to harvest good
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @FXML private void harvest() throws RemoteException {

        client.showVillagerSelection(
            new AllVillagerSelectionFactory(),
            new HarvestAction(this.client.getGameClient()),
            new DoActionStrategy(),
            new MultipleSelectionFactory()
        );

    }

    /**
     * This allows user to train new villagers
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @FXML private void train() throws RemoteException {

        client.showVillagerSelection(
            new TrainerVillagerSelectionFactory(),
            new TrainerAction(this.client.getGameClient()),
            new DoActionStrategy(),
            new SingleSelectionFactory()
        );

    }

    /**
     * This allows user to pass turn
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @FXML private void pass() throws RemoteException {

        client.getGameClient().getPlayer().doAction(new PassAction(this.client.getGameClient()));

    }

    /**
     * For registering client to view
     * @param client
     * @author Robin Silverio
     */
    public void registerClient(Client client) {
        this.client = client;
    }

    /**
     * Method for controlling the amount of available villagers for performing an explore action.
     * If not, then disable the buttons.
     * @param size, count of available villagers
     * @author Robin Silverio
     */
    public void checkAvailableVillagersForExploreAction(int size){
        if (size < 2)
            exploreButton.setDisable(true);
        else
            exploreButton.setDisable(false);
    }

    /**
     * Method for controlling the amount of available villagers for performing a labour action.
     * If not, then disable the buttons.
     * @param size, count of available villagers
     * @author Robin Silverio
     */
    public void checkAvailableVillagersForLabourAction(int size){
        if (size < 1)
            labourButton.setDisable(true);
        else
            labourButton.setDisable(false);
    }

    /**
     * Method for controlling the amount of available villagers for performing a harvest action.
     * If not, then disable the buttons.
     * @param villagerSize, count of available villagers
     * @param buildingSize, count of available buildings
     * @author Robin Silverio
     */
    public void checkAvailableVillagersAndBuildingForHarvest(int villagerSize, int buildingSize){
        if (villagerSize < 1 && buildingSize < 1)
            harvestButton.setDisable(true);
        else
            harvestButton.setDisable(false);
    }

    /**
     * Method for controlling the amount of available villagers for performing a train action.
     * If not, then disable the buttons.
     * @param size, count of available villagers
     * @author Robin Silverio
     */
    public void checkAvailableTrainerVillagersForTraining(int size){
        if (size < 1)
            trainButton.setDisable(true);
        else
            trainButton.setDisable(false);
    }
}
