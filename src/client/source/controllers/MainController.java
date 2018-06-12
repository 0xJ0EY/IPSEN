package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.interfaces.PlayerInterface;

import java.rmi.RemoteException;

/**
 * A class that acts as an intermediary between mainview and models
 */
public class MainController implements ControllerInterface, Observable {

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

    private Thread messageThread;

    /**
     * This is for assigning tabcontainer.
     * @author Joey de Ruiter
     */
    public void initialize() {
        System.out.println("Initialize");
        this.menuController.assignTabContainer(this.tabContainer);
    }


    @Override
    public void updateObserver() {
        this.showMessage(this.client.messageObserver.getState());
    }

    /**
     * Of course, for setting client.
     * @param client
     * @author Joey de Ruiter
     */
    public void setClient(Client client) {
        menuController.registerClient(client);
        aboveController.registerClient(client);
        belowController.registerClient(client);
        marketController.registerClient(client);
        turnController.registerClient(client);
        settingsController.registerClient(client);

        this.client = client;

        // Register this object for the turn callback
        this.client.messageObserver.attach(this);
    }

    /**
     * For showing Main UI
     * @return
     * @author Joey de Ruiter
     */
    @Override
    public Parent show() {
        tabContainer.getSelectionModel().select(0);
        return this.root;
    }

    public void showMessage(String message) {
        if (this.messageThread != null && this.messageThread.isAlive()) this.messageThread.interrupt();

        Runnable r = () -> {
            this.message.setText(message);
            this.message.setVisible(true);

            try {
                Thread.sleep(1750);
            } catch (InterruptedException e) {
                System.out.println("Message interrupted");
            } finally {
                this.message.setVisible(false);
            }
        };

        this.messageThread = new Thread(r);
        this.messageThread.start();
    }
}
