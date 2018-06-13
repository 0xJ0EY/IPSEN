package client.source.controllers;

import client.source.Client;
import client.source.factories.*;
import client.source.strategies.DoActionStrategy;
import client.source.strategies.RequestStrategy;
import javafx.fxml.FXML;
import server.sources.actions.*;
import server.sources.notifications.EndOfGameNotification;

import java.rmi.RemoteException;

/**
 * A class that acts as an intermediate between turnview and models
 * Created by Robin Silverio
 */
public class TurnController {

    private Client client;

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
            new DoActionStrategy(),
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
            new DoActionStrategy(),
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
            new DoActionStrategy(),
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
    }
}
