package client.source.controllers;

import client.source.Client;
import com.sun.scenario.Settings;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MainController implements ControllerInterface {

    private Client client;

    @FXML Parent root;

    @FXML private TabPane tabContainer;

    @FXML private AnchorPane menu;
    @FXML public MenuController menuController;

    @FXML private AnchorPane above;
    @FXML public AboveController aboveController;

    @FXML private AnchorPane below;
    @FXML public BelowController belowController;

    @FXML private AnchorPane market;
    @FXML public MarketController marketController;

    @FXML private AnchorPane turn;
    @FXML public TurnController turnController;

    // TODO: Fix this
//    @FXML private AnchorPane settings;
//    @FXML public SettingsController settingsController;

    @FXML private AnchorPane rules;
    @FXML public RulesController rulesController;

    public void initialize() {
        System.out.println("Initialize");
        this.menuController.assignTabContainer(this.tabContainer);
    }

    public void setClient(Client client) {
        aboveController.setClient(client);
        belowController.setClient(client);
        marketController.setClient(client);
        turnController.setClient(client);
//        settingsController.setClient(client);

        this.client = client;
    }

    @Override
    public Parent show() {
        return this.root;
    }
}
