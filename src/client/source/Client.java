package client.source;

import client.source.controllers.*;
import client.source.controllers.ExplorePartyController;
import client.source.controllers.VillagerSelectionController;
import client.source.factories.ControllerFactory;
import client.source.observers.Observer;
import client.source.strategies.VillagerSelectionStrategy;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.GameClient;
import server.sources.models.stories.Choice;
import server.sources.models.stories.Story;
import client.source.factories.VillagerSelectionFactory;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client extends Application {

    private Stage stage;
    private GameClient gameClient;

    private ControllerFactory controllerFactory;

    private MainController main;

    public Observer<String> messageObserver = new Observer<>();
    public Observer<PlayerInterface> turnObserver = new Observer<>();
    public Observer<ArrayList<PlayerInterface>> clientObserver = new Observer<>();

    @Override
    public void start(Stage primaryStage) {

        controllerFactory = new ControllerFactory(this);

        this.stage = primaryStage;
        this.stage.setTitle("Above and Below");

        this.main = controllerFactory.createMain();

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
        this.setScene(controllerFactory.createLogin().show());
    }

    public void showLobby() {
        this.setScene(controllerFactory.createLobby().show());
    }

    public void showMain() {
        this.setScene(this.main.show());
    }

    public void showVillagerSelection(VillagerSelectionFactory factory, VillagerActionInterface action, VillagerSelectionStrategy strategy) {
        VillagerSelectionController selection = controllerFactory.createVillagerSelection();

        selection.setFactory(factory);
        selection.setVillagerAction(action);
        selection.setStrategy(strategy);

        this.setScene(selection.show());
    }

    public void showExplore(Story story) {
        ExploreController explore = controllerFactory.createExplore();
        explore.setExploreStory(story);
        this.setScene(explore.show());
    }

    public Stage getStage() {
        return this.stage;
    }


    public void showParty(Story story, Choice choice){
        ExplorePartyController party = controllerFactory.createExploreParty();
        party.setStory(story);
        party.setChoice(choice);
        this.setScene(party.show());
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

    public static void main(String[] args) {
        launch(args);
    }

    public void showMessage(String message) {
        this.main.showMessage(message);
    }

}
