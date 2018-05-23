package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import server.sources.actions.TestAction;

import java.rmi.RemoteException;

public class TurnController {

    private Client client;

    @FXML private void onClickAction() throws RemoteException {

        client.gameClient.getPlayer().doAction(new TestAction());
        System.out.println("Send action");

    }

    public void setClient(Client client) {
        this.client = client;
    }
}
