package client.source.controllers;

import client.source.Client;
import client.source.factories.AllVillagerSelectionFactory;
import client.source.factories.BuilderVillagerSelectionFactory;
import client.source.factories.TrainerVillagerSelectionFactory;
import client.source.factories.VillagerSelectionFactory;
import javafx.fxml.FXML;
import server.sources.actions.*;

import java.rmi.RemoteException;

public class TurnController {

    private Client client;

    /**
     * This allows user to do the explore action
     * @throws RemoteException
     */
    @FXML private void explore() throws RemoteException {

        client.getVillagerSelection().setFactory(new AllVillagerSelectionFactory());
        client.getVillagerSelection().setVillagerAction(new ExploreStoryAction(this.client.getGameClient()));
        client.showVillagerSelection();

    }

    /**
     * This allows user to do the build action.
     * @throws RemoteException
     */
    @FXML private void build() throws RemoteException {

        client.getVillagerSelection().setFactory(new BuilderVillagerSelectionFactory());
        client.getVillagerSelection().setVillagerAction(new BuildAction());
        client.showVillagerSelection();

    }

    /**
     * This allows user to do labor action in order to get coins
     * @throws RemoteException
     */
    @FXML private void labor() throws RemoteException {

        client.getGameClient().getPlayer().doAction(new TestAction());
        System.out.println("Send action labor");

    }

    /**
     * This allows user to do a harvest action to harvest goods
     * @throws RemoteException
     */
    @FXML private void harvest() throws RemoteException {

        client.getGameClient().getPlayer().doAction(new TestAction());
        System.out.println("Send action harvest");

    }

    /**
     * This allows user to train new villagers
     * @throws RemoteException
     */
    @FXML private void train() throws RemoteException {

        client.getVillagerSelection().setFactory(new TrainerVillagerSelectionFactory());
        client.getVillagerSelection().setVillagerAction(new TrainerAction());
        client.showVillagerSelection();

    }

    /**
     * This allows user to pass turn
     * @throws RemoteException
     */
    @FXML private void pass() throws RemoteException {

        client.getGameClient().getPlayer().doAction(new PassAction(this.client.getGameClient()));
        System.out.println("Send action pass");

    }

    public void registerClient(Client client) {
        this.client = client;
    }
}
