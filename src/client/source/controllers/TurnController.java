package client.source.controllers;

import client.source.Client;
import client.source.factories.*;
import client.source.strategies.DoActionStrategy;
import client.source.strategies.RequestStrategy;
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

        client.showVillagerSelection(
            new AllVillagerSelectionFactory(),
            new ExploreStoryAction(this.client.getGameClient()),
            new RequestStrategy(),
            new MultipleSelectionFactory()
        );

    }

    /**
     * This allows user to do the build action.
     * @throws RemoteException
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
     * @throws RemoteException
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
     * This allows user to do a harvest action to harvest goods
     * @throws RemoteException
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
     * @throws RemoteException
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
     * @throws RemoteException
     */
    @FXML private void pass() throws RemoteException {

        client.getGameClient().getPlayer().doAction(new PassAction(this.client.getGameClient()));

    }

    public void registerClient(Client client) {
        this.client = client;
    }
}
