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
        ArrayList<String> clientNames = new ArrayList<String>(); // TODO: Look for a way to remove this

        for (GameClientInterface client : currentClients) {
            clientNames.add(client.getUsername());
        }

        ObservableList<String> listItems = FXCollections.observableArrayList(clientNames);
        lobbyList.setItems(listItems);

    }

    public void enableStartButton() {
        buttonStart.setDisable(false);
    }

    public void onClickStart() throws RemoteException {
        this.client.gameClient.requestRequest(new StartGameRequest());
    }

    public void onClickDisconnect() throws RemoteException {
        this.client.gameClient.disconnect();
    }

    @Override
    public Parent show() {
        return this.root;
    }
}
