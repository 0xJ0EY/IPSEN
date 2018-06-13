package client.source;

import client.source.components.villager_to_train.TrainerVillagerComponent;
import client.source.controllers.*;
import client.source.controllers.ExplorePartyController;
import client.source.controllers.VillagerSelectionController;
import client.source.factories.ControllerFactory;
import client.source.factories.VillagerSelectionComponentFactory;
import client.source.observers.Observer;
import client.source.strategies.VillagerSelectionStrategy;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.sources.controllers.GoodOnSale;
import server.sources.actions.HarvestAction;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.GameClient;
import server.sources.models.stories.Choice;
import server.sources.models.stories.Option;
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
    public Observer<PlayerBoardInterface> playerBoardObserver = new Observer<>();

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

    public void showVillagerSelection(
            VillagerSelectionFactory villagerFactory,
            VillagerActionInterface action,
            VillagerSelectionStrategy strategy,
            VillagerSelectionComponentFactory componentFactory
    ) {

        VillagerSelectionController selection = controllerFactory.createVillagerSelection();

        selection.setVillagerFactory(villagerFactory);
        selection.setVillagerAction(action);
        selection.setStrategy(strategy);
        selection.setComponentFactory(componentFactory);

        this.setScene(selection.show());
    }

    public void showExplore(Story story) {
        ExploreController explore = controllerFactory.createExplore();
        explore.setExploreStory(story);
        this.setScene(explore.show());
    }

    public void showHarvestSelection(HarvestAction harvestAction) throws RemoteException{
        HarvestController harvest = controllerFactory.createHarvestSelection();
        harvest.setHarvest(harvestAction);
        harvest.load();
        this.setScene(harvest.show());
    }

    public void showRewards(Option option) {
        RewardController rewards = controllerFactory.createRewardView();
        rewards.setRewards(option.getRewards());
        this.setScene(rewards.show());
    }

    public void showBuild(){
        BuildController build = controllerFactory.createBuild();
        build.load();
        this.setScene(build.show());
    }

    public void showTrain() {
        TrainController train = controllerFactory.createTrain();
        this.setScene(train.show());
    }

    public void showTrainReward(TrainerVillagerComponent villager) throws RemoteException {
        TrainRewardController trainReward = controllerFactory.createTrainRewardView();
        trainReward.setTrainReward(villager);
        this.setScene(trainReward.show());
    }

    public void showParty(Story story, Choice choice){
        ExplorePartyController party = controllerFactory.createExploreParty();
        party.setStory(story);
        party.setChoice(choice);
        this.setScene(party.show());
    }

    public void showSellableGoods(GameClientInterface client) throws RemoteException{
        SellGoodController goods = controllerFactory.createSellGoodController();
        goods.setClient(client);

        goods.load();
        this.setScene(goods.show());

    }

    public void showBuyableGoods(GameClientInterface client) throws RemoteException{
        BuyGoodController goods = controllerFactory.createBuyGoodController();
        goods.setClient(client);

        goods.load();
        this.setScene(goods.show());
    }

    public void showScoreBoard(){
        ScoreboardController score = controllerFactory.createScoreBoard();
        this.setScene(score.show());
    }

    public void showVillagerRest(){
        this.setScene(controllerFactory.createVillagerRest().show());
    }

    public Stage getStage(){
        return this.stage;
    }

    private void setScene(Parent root){
        Scene scene = stage.getScene();

        if (scene == null) {
            stage.setScene(new Scene(root, 680 ,700));
        } else {
            Platform.runLater(() -> scene.setRoot(root));
        }
    }

    public void setMaximized() {
        Platform.runLater(() -> this.stage.setMaximized(true));
    }

    public GameClient getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    public void showMessage(String message) {
        this.main.showMessage(message);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
