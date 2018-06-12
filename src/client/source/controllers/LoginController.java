package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.sources.Server;
import server.sources.exceptions.GameStartedException;
import server.sources.exceptions.ServerFullException;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * A class that acts as an intermediary between a loginview and models
 */
public class LoginController implements ControllerInterface {

    private Client client;

    @FXML private Parent root;

    @FXML private TextField address;

    @FXML private TextField username;

    @FXML private Label errorAddress;

    @FXML private Label errorUsername;

    @FXML
    private boolean onClickConnect() throws RemoteException {

        if (!this.validateUsername(username.getText())) {
            this.errorUsername.setText("Invalid username.");
            return false;
        }

        try {
            this.client.getGameClient().connect(address.getText(), username.getText());
            return true;
        } catch (ServerFullException e) {
            this.errorAddress.setText("The game is full.");

        } catch (GameStartedException e) {
            this.errorAddress.setText("Game has already started.");

        } catch (RemoteException | NotBoundException e) {
            this.errorAddress.setText("Server did not respond.");
            e.printStackTrace();

        } catch (MalformedURLException e) {
            this.errorAddress.setText("Invalid url.");
            e.printStackTrace();

        }

        return false;
    }

    /**
     * Initiates the server.
     * @return boolean state (TRUE or FALSE)
     * @author Joey de Ruiter
     */
    public boolean onClickServer() {
        // Start a server in the background and connect to it

        if (!this.validateUsername(username.getText())) {
            this.errorUsername.setText("Invalid username.");
            return false;
        }

        try {
            new Server(new String[0]);
            this.client.getGameClient().connect("localhost", username.getText());
            return true;

        } catch (ServerFullException e) {
            this.errorAddress.setText("The game is full.");

        } catch (GameStartedException e) {
            this.errorAddress.setText("Game has already started.");

        } catch (RemoteException | NotBoundException e) {
            this.errorAddress.setText("Server already running.");
            e.printStackTrace();

        } catch (MalformedURLException e) {
            this.errorAddress.setText("Invalid url.");
            e.printStackTrace();

        }

        return false;
    }

    /**
     * For setting a client.
     * @param client Client
     * @author Joey de Ruiter
     */
    public void setClient(Client client) {
        this.client = client;
    }

    private boolean validateUsername(String username) {
        return username.length() > 0;
    }

    /**
     * For displaying a login view.
     * @return login view
     * @author Joey de Ruiter
     */
    @Override
    public Parent show() {
        return this.root;
    }

}
