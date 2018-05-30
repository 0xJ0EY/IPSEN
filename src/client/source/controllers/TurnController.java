package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import server.sources.actions.ExploreStoryAction;
import server.sources.actions.PassAction;
import server.sources.actions.TestAction;

import java.rmi.RemoteException;

public class TurnController {

    private Client client;

    /**
     * This allows user to do the explore action
     * @throws RemoteException
     */
    @FXML private void explore() throws RemoteException {
        client.getGameClient().requestAction(new ExploreStoryAction(this.client.getGameClient()));

        System.out.println("Send action explore");

    }

    /**
     * This allows user to do the build action.
     * @throws RemoteException
     */
    @FXML private void build() throws RemoteException {

        client.getGameClient().getPlayer().doAction(new TestAction());
        System.out.println("Send action build");

    }

    /**
     * This allows user to do labour action in order to get coins
     * @throws RemoteException
     */
    @FXML private void labour() throws RemoteException {

        client.getGameClient().getPlayer().doAction(new TestAction());
        System.out.println("Send action labour");

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

        client.getGameClient().getPlayer().doAction(new TestAction());
        System.out.println("Send action train");

    }

    /**
     * This allows user to pass turn
     * @throws RemoteException
     */
    @FXML private void pass() throws RemoteException {

        client.getGameClient().getPlayer().doAction(new PassAction(this.client.getGameClient()));
        System.out.println("Send action pass");

    }

    public void setClient(Client client) {
        this.client = client;
    }
}
