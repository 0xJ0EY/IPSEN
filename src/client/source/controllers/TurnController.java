package client.source.controllers;

import client.source.Client;
import client.source.factories.*;
import client.source.observers.Observable;
import client.source.strategies.DoActionStrategy;
import client.source.strategies.RequestStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import server.sources.actions.*;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.models.Player;
import server.sources.models.PlayerBoard;
import server.sources.notifications.EndOfGameNotification;

import java.rmi.RemoteException;

/**
 * A class that acts as an intermediate between turnview and models
 * Created by Robin Silverio
 */
public class TurnController implements Observable {

    private Client client;

    @FXML private Button exploreButton;
    @FXML private Button labourButton;
    @FXML private Button harvestButton;
    @FXML private Button trainButton;
    @FXML private Button buildButton;

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
            new MultipleSelectionFactory(),
                2,
                0
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
            new SingleSelectionFactory(),
                1,
                0
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
            new RequestStrategy(),
            new MultipleSelectionFactory(),
                1,
                0
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
            new RequestStrategy(),
            new MultipleSelectionFactory(),
                1,
                0 // Has to be equal to amount of possible harvest actions
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
            new RequestStrategy(),
            new SingleSelectionFactory(),
                1,
                0
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
        this.client.turnObserver.attach(this);
    }

    /**
     * Method for controlling the amount of available villagers for performing an explore action.
     * If not, then disable the buttons.
     * @param size, count of available villagers
     * @author Robin Silverio
     */
    public void checkAvailableVillagersForExploreAction(int size){
        exploreButton.setDisable(size < 2);
    }

    /**
     * Method for controlling the amount of available villagers for performing a labour action.
     * If not, then disable the buttons.
     * @param size, count of available villagers
     * @author Robin Silverio
     */
    public void checkAvailableVillagersForLabourAction(int size){
        labourButton.setDisable(size < 1);
    }

    /**
     * Method for controlling the amount of available villagers for performing a harvest action.
     * If not, then disable the buttons.
     * @param villagerSize, count of available villagers
     * @param buildingSize, count of available buildings
     * @author Robin Silverio
     */
    public void checkAvailableVillagersAndBuildingForHarvest(int villagerSize, int buildingSize){
        harvestButton.setDisable(villagerSize < 1 || buildingSize < 1);
    }

    /**
     * Method for controlling the amount of available villagers for performing a train action.
     * If not, then disable the buttons.
     * @param size, count of available villagers
     * @author Robin Silverio
     */
    public void checkAvailableTrainerVillagersForTraining(int size){
        trainButton.setDisable(size < 1);
    }

    /**
     * Method for controlling the amount of available villagers for performing a build action.
     * If not, then disable the buttons.
     * @param size, count of available villagers
     * @author Robin Silverio
     */
    public void checkAvailableBuilderVillagersForBuild(int size){
        buildButton.setDisable(size < 1);
    }

    @Override
    public void updateObserver() {
        // Show or hide turn buttons
        PlayerInterface target = this.client.turnObserver.getState();

        // No target, so its not even worth going here
        if (target == null) return;

        try {
            boolean turn = target.getGameClient().equals(this.client.getGameClient());
            PlayerBoardInterface playerBoard = target.getPlayerBoard();

            int availableVillagers = playerBoard.listAvailableVillagers().size();
            int availableTrainerVillagers = playerBoard.listAvailableTrainerVillagers().size();
            int availableBuilderVillagers = playerBoard.listAvailableBuilderVillagers().size();
            int availableBuildings = playerBoard.getHarvestBuildings().size();



            // This is for enabling and disabling buttons in turnmarket view.
            this.checkAvailableVillagersForExploreAction(availableVillagers);
            this.checkAvailableVillagersForLabourAction(availableVillagers);
            this.checkAvailableVillagersAndBuildingForHarvest(availableVillagers, availableBuildings);
            this.checkAvailableTrainerVillagersForTraining(availableTrainerVillagers);
            this.checkAvailableBuilderVillagersForBuild(availableBuilderVillagers);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
