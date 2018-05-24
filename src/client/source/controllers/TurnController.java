package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import server.sources.actions.TestAction;

import java.rmi.RemoteException;

public class TurnController {

    private Client client;


    @FXML private void explore() throws RemoteException {

        client.gameClient.getPlayer().doAction(new TestAction());
        System.out.println("Send action explore");

    }

    @FXML private void build() throws RemoteException {

        client.gameClient.getPlayer().doAction(new TestAction());
        System.out.println("Send action build");

    }
    @FXML private void labour() throws RemoteException {

        client.gameClient.getPlayer().doAction(new TestAction());
        System.out.println("Send action labour");

    }
    @FXML private void harvest() throws RemoteException {

        client.gameClient.getPlayer().doAction(new TestAction());
        System.out.println("Send action harvest");

    }
    @FXML private void train() throws RemoteException {

        client.gameClient.getPlayer().doAction(new TestAction());
        System.out.println("Send action train");

    }
    @FXML private void pass() throws RemoteException {

        client.gameClient.getPlayer().doAction(new TestAction());
        System.out.println("Send action pass");

    }

    public void setClient(Client client) {
        this.client = client;
    }
}
