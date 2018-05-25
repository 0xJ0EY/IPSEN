package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;

public class ExploreController implements ControllerInterface {

    private Client client;

    @FXML Parent root;










    @Override
    public Parent show() {
        return this.root;
    }
}
