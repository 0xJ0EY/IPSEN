package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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

    @FXML private AnchorPane settings;
    @FXML public SettingsController settingsController;

    @FXML private AnchorPane rules;
    @FXML public RulesController rulesController;

    @FXML private Text message;

    /**
     * This is for assigning tabcontainer.
     */
    public void initialize() {
        System.out.println("Initialize");
        this.menuController.assignTabContainer(this.tabContainer);
    }

    /**
     * Of course, for setting client.
     * @param client
     */
    public void setClient(Client client) {
        aboveController.setClient(client);
        belowController.setClient(client);
        marketController.setClient(client);
        turnController.setClient(client);
        settingsController.setClient(client);

        this.client = client;
    }

    /**
     * For showing Main UI
     * @return
     */
    @Override
    public Parent show() {
        tabContainer.getSelectionModel().select(0);
        return this.root;
    }

    public void showMessage(String message) {

        Runnable r = () -> {
            this.message.setText(message);
            this.message.setVisible(true);

            try {
                Thread.sleep(1750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.message.setVisible(false);

        };

        new Thread(r).start();

    }
}
