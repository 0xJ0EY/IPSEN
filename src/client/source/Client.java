package client.source;

import client.source.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.sources.GameClient;
import server.sources.models.stories.Story;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Client extends Application implements Serializable {

    private Stage stage;
    private GameClient gameClient;

    private LoginController login;
    private LobbyController lobby;

    private MainController main;
    private ExploreController explore;

    @Override
    public void start(Stage primaryStage) {

        // Set game client
        try {
            this.setGameClient(new GameClient(this));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Load all possible views
        try {
            this.loadLogin();
            this.loadLobby();
            this.loadMain();
            this.loadExplore();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.stage = primaryStage;
        this.stage.setTitle("Above and Below");

        this.showLogin();

        this.stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Disconnect");
        getGameClient().disconnect();
        super.stop();
    }

    private void loadLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/views/login.fxml"));
        loader.load();

        this.setLogin(loader.getController());
        this.getLogin().setClient(this);

    }

    private void loadLobby() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/views/lobby.fxml"));
        loader.load();

        this.setLobby(loader.getController());
        this.getLobby().setClient(this);

    }

    private void loadMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/views/main.fxml"));
        loader.load();

        this.setMain(loader.getController());
        this.getMain().setClient(this);

    }

    private void loadExplore() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/views/explore.fxml"));
        loader.load();

        this.explore = loader.getController();
        this.explore.setClient(this);
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

    public void showExplore(Story story) {
        this.explore.setExploreStory(story);
        this.setScene((this.explore.show()));
    }

    private void setScene(Parent root) {
        Scene scene = stage.getScene();

        if (scene == null) {
            stage.setScene(new Scene(root, 1000 ,600));
        } else {
            scene.setRoot(root);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public LobbyController getLobby() {
        return lobby;
    }

    public void setLobby(LobbyController lobby) {
        this.lobby = lobby;
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

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public MainController getMain() {
        return main;
    }

    public void setMain(MainController main) {
        this.main = main;
    }

    public ExploreController getExplore() {
        return explore;
    }

    public void setExplore(ExploreController explore) {
        this.explore = explore;
    }
}
