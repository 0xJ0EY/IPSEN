package client.source.controllers;

import client.source.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.models.Player;
import server.sources.requests.StartGameRequest;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LobbyController implements ControllerInterface {

    private Client client;

    @FXML private Parent root;

    @FXML private ListView lobbyList;

    @FXML private Button buttonStart;

    /**
     * For setting a client
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * This is for updating a list of connected players in a lobby,
     * ready to play the game environment.
     * @throws RemoteException
     */
    public void updateLobbyList() throws RemoteException {

        // Load models
        ObservableList<String> listItems = FXCollections.observableArrayList();
        ArrayList<PlayerInterface> players = this.client.getGameClient().getServer().getGame().listCurrentPlayers();

        for (PlayerInterface player : players) {
            listItems.add(player.getUsername());
        }

        // Add them in lobby list
        lobbyList.setItems(listItems);

    }

    /**
     * This is for enabling and disabling buttons
     */
    public void enableStartButton() {
        buttonStart.setDisable(false);
    }

    public void disableStartButton() {
        buttonStart.setDisable(true);
    }

    public void onClickStart() throws RemoteException {
        this.client.getGameClient().requestRequest(new StartGameRequest());
    }

    /**
     * Disconnect the client from the game
     * @throws RemoteException
     */
    public void onClickDisconnect() throws RemoteException {

        // Disconnect from the game lobby / game
        this.client.getGameClient().disconnect();

        // Disable the start button if it was active
        this.disableStartButton();

        // Return the client to the login screen
        this.client.showLogin();
    }

    /**
     * Shows Lobby UI
     * @return root
     */
    @Override
    public Parent show() {
        return this.root;
    }
}
