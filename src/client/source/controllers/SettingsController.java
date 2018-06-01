package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import server.sources.requests.SaveGameRequest;

import java.rmi.RemoteException;

public class SettingsController {

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    private void onClickSave() throws RemoteException {
        client.getGameClient().requestRequest(new SaveGameRequest());
    }
}
