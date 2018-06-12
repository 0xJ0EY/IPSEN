package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import server.sources.requests.SaveGameRequest;

import java.rmi.RemoteException;

public class SettingsController {

    private Client client;

    /**
     * For registering clients in settingsview.
     * @param client
     */
    public void registerClient(Client client) {
        this.client = client;
    }

    /**
     * Saves the game session/
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    @FXML
    private void onClickSave() throws RemoteException {
        client.getGameClient().requestRequest(new SaveGameRequest(client.getGameClient()));
    }
}
