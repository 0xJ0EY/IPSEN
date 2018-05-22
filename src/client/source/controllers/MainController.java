package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MainController implements ControllerInterface {

    private Client client;

    @FXML Parent root;

    @FXML private TabPane tabContainer;

    @FXML private AnchorPane menu;
    @FXML private MenuController menuController;

    @FXML private AnchorPane above;
    @FXML private AboveController aboveController;

    @FXML private AnchorPane below;
    @FXML private BelowController belowController;

    public void initialize() {
        System.out.println("Initialize");
        this.menuController.assignTabContainer(this.tabContainer);
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public Parent show() {
        return this.root;
    }
}
