package client.source;

import client.source.controllers.LobbyController;
import client.source.controllers.LoginController;
import client.source.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.sources.GameClient;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Client extends Application implements Serializable {

    private Stage stage;
    public GameClient gameClient;

    public LoginController login;
    private LobbyController lobby;
    public MainController main;

    @Override
    public void start(Stage primaryStage) {

        // Set game client
        try {
            this.gameClient = new GameClient(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Load all possible views
        try {
            this.loadLogin();
            this.loadLobby();
            this.loadMain();

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
        gameClient.disconnect();
        super.stop();
    }

    private void loadLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/views/login.fxml"));
        loader.load();

        this.login = loader.getController();
        this.login.setClient(this);

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

        this.main = loader.getController();
        this.main.setClient(this);

    }

    public void showLogin() {
        this.setScene(this.login.show());
    }

    public void showLobby() {
        this.setScene(this.getLobby().show());
    }

    public void showMain() {
        this.setScene(this.main.show());
    }

    private void setScene(Parent root) {
        Scene scene = stage.getScene();

        if (scene == null) {
            stage.setScene(new Scene(root, 1000 ,950));
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
}
