package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.requests.LoadGameRequest;
import server.sources.requests.StartGameRequest;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A Class that acts as a intermediary between a lobbyview and model.
 * Created by Joey de Ruiter
 */
public class LobbyController implements ControllerInterface, Observable {

    protected Client client;

    @FXML protected Parent root;

    @FXML protected ListView lobbyList;

    @FXML protected Button buttonStart;

    @FXML protected Button buttonLoad;

    private ObservableList<String> listItems = FXCollections.observableArrayList();

    /**
     * For setting a client
     * @param client Client
     */
    public void registerClient(Client client) throws RemoteException {
        this.client = client;

        // Register the client as observer
        this.client.clientObserver.attach(this);
    }

    /**
     * Loads all registrated clients in a listview of lobbyview
     * @author Joey de Ruiter
     */
    @FXML
    public void initialize() {
        this.lobbyList.setItems(this.listItems);
    }

    /**
     * Observes all updates of lobbyview
     * @author Joey de Ruiter
     */
    @Override
    public void updateObserver() {
        ArrayList<PlayerInterface> players = this.client.clientObserver.getState();
        this.listItems.clear();

        for (PlayerInterface player : players) {
            try {
                listItems.add(player.getUsername());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        try {
            buttonStart.setDisable(!this.client.getGameClient().isOwner());
            buttonLoad.setDisable(!this.client.getGameClient().isOwner());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a game, allowing clients to enter the game environment.
     * @throws RemoteException java.rmi.RemoteException
     */
    @FXML
    void onClickStart() throws RemoteException {
        this.client.getGameClient().requestRequest(new StartGameRequest());
    }

    /**
     * Loads saved game.
     * @author Joey de Ruiter
     */
    @FXML
    public void onClickLoad() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select save game");

        // Set filter for only .uml files
        FileChooser.ExtensionFilter filters = new FileChooser.ExtensionFilter("Save games", "*.uml");
        fileChooser.getExtensionFilters().add(filters);

        // Set default directory to ~/Documents
        String directory = System.getProperty("user.home") + File.separator + "Documents";
        File defaultDir = new File(directory);

        if (!defaultDir.canRead()) {
            defaultDir = new File("/");
        }

        fileChooser.setInitialDirectory(defaultDir);

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
     * @throws RemoteException java.rmi.RemoteException
     */
    @FXML
    public void onClickDisconnect() throws RemoteException {

        // Disconnect from the gameController lobby / gameController
        this.client.getGameClient().disconnect();

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
