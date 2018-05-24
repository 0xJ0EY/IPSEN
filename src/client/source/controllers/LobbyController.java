package client.source.controllers;

import client.source.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import server.sources.interfaces.GameClientInterface;
import server.sources.requests.StartGameRequest;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LobbyController implements ControllerInterface {

    private Client client;

    @FXML private Parent root;

    @FXML private ListView lobbyList;

    @FXML private Button buttonStart;

    public void setClient(Client client) {
        this.client = client;
    }

    public void updateLobbyList() throws RemoteException {
        ArrayList<GameClientInterface> currentClients = this.client.gameClient.getServer().listCurrentClients();
        ArrayList<String> clientNames = new ArrayList<String>();

        for (GameClientInterface client : currentClients) {
            clientNames.add(client.getUsername());
        }

        ObservableList<String> listItems = FXCollections.observableArrayList(clientNames);
        lobbyList.setItems(listItems);

    }

    public void enableStartButton() {
        buttonStart.setDisable(false);
    }

    public void disableStartButton() {
        buttonStart.setDisable(true);
    }

    public void onClickStart() throws RemoteException {
        this.client.gameClient.requestRequest(new StartGameRequest());
    }

    /**
     * Disconnect the client from the game
     * @throws RemoteException
     */
    public void onClickDisconnect() throws RemoteException {

        // Disconnect from the game lobby / game
        this.client.gameClient.disconnect();

        // Disable the start button if it was active
        this.disableStartButton();

        // Return the client to the login screen
        this.client.showLogin();
    }

    @Override
    public Parent show() {
        return this.root;
    }
}
