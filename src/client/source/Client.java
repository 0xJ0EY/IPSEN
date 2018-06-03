package client.source;

import client.source.controllers.*;
import client.source.controllers.ExplorePartyController;
import client.source.controllers.VillagerSelectionController;
import client.source.factories.ControllerFactory;
import client.source.observers.Observer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.sources.interfaces.PlayerInterface;
import server.sources.models.GameClient;
import server.sources.models.stories.Story;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client extends Application implements Serializable {

    private Stage stage;
    private GameClient gameClient;

    private LoginController login;
    private LobbyController lobby;

    private MainController main;
    private ExploreController explore;

    private VillagerSelectionController villagerSelection;
    private ExplorePartyController exploreParty;

    public Observer<ArrayList<PlayerInterface>> clientObserver = new Observer<>();

    @Override
    public void start(Stage primaryStage) {

        ControllerFactory controllerFactory = new ControllerFactory(this);

        this.stage = primaryStage;
        this.stage.setTitle("Above and Below");

        // Load the views and set the controllers

        // TODO: Only start loading the controllers when they are actually required.
        this.login = controllerFactory.createLogin();
        this.lobby = controllerFactory.createLobby();
        this.main = controllerFactory.createMain();
        this.explore = controllerFactory.createExplore();
        this.villagerSelection = controllerFactory.createVillagerSelection();
        this.exploreParty = controllerFactory.createExploreParty();

        // Set gameController client
        try {
            this.setGameClient(new GameClient(this));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.showLogin();

        this.stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Disconnect");
        getGameClient().disconnect();
        super.stop();
    }

    public void showLogin() {
        this.setScene(this.login.show());
    }

    public void showLobby() {
        this.setScene(this.lobby.show());
    }

    public void showMain() {
        this.setScene(this.getMain().show());
    }

    public void showVillagerSelection() {
        this.setScene(this.getVillagerSelection().show());
    }

    public void showExplore(Story story) {
        this.explore.setExploreStory(story);
        this.setScene((this.explore.show()));
    }

    public Stage getStage() {
        return this.stage;
    }

    public void showParty(Story story){
        try {
            this.exploreParty.setStory(story);
            this.setScene(this.exploreParty.show());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setScene(Parent root) {
        Scene scene = stage.getScene();

        if (scene == null) {
            stage.setScene(new Scene(root, 680 ,700));
        } else {
            scene.setRoot(root);
        }
    }

    public GameClient getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClient gameClient) {
        this.gameClient = gameClient;
    }


    public MainController getMain() {
        return main;
    }

    public ExploreController getExplore() {
        return explore;
    }

    public VillagerSelectionController getVillagerSelection() {
        return villagerSelection;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
