package client.source;

import client.source.controllers.*;
import client.source.controllers.VillagerSelectionController;
import client.source.factories.ControllerFactory;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.sources.models.GameClient;
import server.sources.models.stories.Story;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Client extends Application implements Serializable {

    private Stage stage;
    private GameClient gameClient;

    private LoginController login;
    private LobbyController lobby;

    private MainController main;
    private ExploreController explore;

    private VillagerSelectionController villagerSelection;

    @Override
    public void start(Stage primaryStage) {

        ControllerFactory controllerFactory = new ControllerFactory(this);

        this.stage = primaryStage;
        this.stage.setTitle("Above and Below");

        // Load the views and set the controllers
        this.login = controllerFactory.createLogin();
        this.lobby = controllerFactory.createLobby();
        this.main = controllerFactory.createMain();
        this.explore = controllerFactory.createExplore();
        this.villagerSelection = controllerFactory.createVillagerSelection();

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
        this.setScene(this.getLogin().show());
    }

    public void showLobby() {
        this.setScene(this.getLobby().show());
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

    private void setScene(Parent root) {
        Scene scene = stage.getScene();

        if (scene == null) {
            stage.setScene(new Scene(root, 680 ,700));
        } else {
            scene.setRoot(root);
        }
    }

    public LobbyController getLobby() {
        return lobby;
    }

    public GameClient getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    public LoginController getLogin() {
        return login;
    }

    public MainController getMain() {
        return main;
    }

    public ExploreController getExplore() {
        return explore;
    }

    public Stage getStage() {
        return stage;
    }

    public VillagerSelectionController getVillagerSelection() {
        return villagerSelection;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
