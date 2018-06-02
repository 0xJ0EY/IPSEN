package client.source.controllers;

import client.source.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import server.sources.interfaces.PlayerInterface;
import server.sources.requests.LoadGameRequest;
import server.sources.requests.StartGameRequest;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class LobbyController implements ControllerInterface {

    private Client client;

    @FXML private Parent root;

    @FXML private ListView lobbyList;

    @FXML private Button buttonStart;

    @FXML private Button buttonLoad;

    /**
     * For setting a client
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * This is for updating a list of connected players in a lobby,
     * ready to play the gameController environment.
     * @throws RemoteException
     */
    public void updateLobbyList() throws RemoteException {

        // Load models
        ObservableList<String> listItems = FXCollections.observableArrayList();
        ArrayList<PlayerInterface> players = this.client.getGameClient().getServer().getGameController().listCurrentPlayers();

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

    public void enableLoadButton() {
        buttonLoad.setDisable(false);
    }

    public
    void disableLoadButton() {
        buttonLoad.setDisable(true);
    }

    @FXML
    void onClickStart() throws RemoteException {
        this.client.getGameClient().requestRequest(new StartGameRequest());
    }

    @FXML
    public void onClickLoad() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select save game");

        // Set filter for only .uml files
        FileChooser.ExtensionFilter filters = new FileChooser.ExtensionFilter("Save games", "*.uml");
        fileChooser.getExtensionFilters().add(filters);

        File file = fileChooser.showOpenDialog(client.getStage());

        if (file == null) return;

        try {
            this.client.getGameClient().requestRequest(new LoadGameRequest(file));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect the client from the gameController
     * @throws RemoteException
     */
    @FXML
    public void onClickDisconnect() throws RemoteException {

        // Disconnect from the gameController lobby / gameController
        this.client.getGameClient().disconnect();

        // Disable the start button if it was active
        this.disableStartButton();

        // Disable the load button if it was active
        this.disableLoadButton();

        // Disable the settings button if it was active
        this.client.getMain().menuController.disableSettingsButton();

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
