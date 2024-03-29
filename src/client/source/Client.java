package client.source;

import client.source.components.building.BuildingComponent;
import client.source.components.reward.GoodRewardComponent;
import client.source.components.villager.MarketVillagerComponent;
import client.source.components.villager_to_train.TrainerVillagerComponent;
import client.source.controllers.*;
import client.source.factories.ControllerFactory;
import client.source.factories.VillagerSelectionComponentFactory;
import client.source.factories.VillagerSelectionFactory;
import client.source.observers.Observer;
import client.source.strategies.VillagerSelectionStrategy;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.sources.actions.HarvestAction;
import server.sources.interfaces.*;
import server.sources.models.GameClient;
import server.sources.models.stories.Choice;
import server.sources.models.stories.Option;
import server.sources.models.stories.Story;
import server.sources.models.stories.rewards.GoodReward;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client extends Application {

    private Stage stage;
    private GameClient gameClient;
    private ArrayList<VillagerInterface> selectedVillagers;

    private ControllerFactory controllerFactory;

    private MainController main;

    public Observer<String> messageObserver = new Observer<>();
    public Observer<PlayerInterface> turnObserver = new Observer<>();
    public Observer<ArrayList<PlayerInterface>> clientObserver = new Observer<>();
    public Observer<PlayerBoardInterface> playerBoardObserver = new Observer<>();
    public Observer<MarketInterface> marketObserver = new Observer<>();
    public Observer<GameControllerInterface> gameObserver = new Observer<>();
    public Observer<ReputationBoardInterface> reputationBoardObserver = new Observer<>();

    /**
     * Show the starting page of the app
     * @param primaryStage Stage
     */
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

    /**
     * Stop the app, and if still connected, disconnect.
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        System.out.println("Disconnect");
        getGameClient().disconnect();
        super.stop();
    }

    /**
     * Show login view
     */
    public void showLogin() {
        this.setScene(controllerFactory.createLogin().show());
    }

    /**
     * Show lobby view
     */
    public void showLobby() {
        this.setScene(controllerFactory.createLobby().show());
    }

    /**
     * Show main view
     */
    public void showMain() {
        this.setScene(this.main.show());
    }

    /**
     * Show villager selection screen
     *
     * @author Joey de Ruiter
     * @param villagerFactory VillagerSelectionFactory
     * @param action VillagerActionInterface
     * @param strategy VillagerSelectionStrategy
     * @param componentFactory VillagerSelectionComponentFactory
     * @param minimumRequiredVillagers minimumRequiredVillagers
     * @param maximumRequiredVillagers maximumRequiredVillagers
     */
    public void showVillagerSelection(
            VillagerSelectionFactory villagerFactory,
            VillagerActionInterface action,
            VillagerSelectionStrategy strategy,
            VillagerSelectionComponentFactory componentFactory,
            int minimumRequiredVillagers,
            int maximumRequiredVillagers
    ) {

        VillagerSelectionController selection = controllerFactory.createVillagerSelection();

        selection.setVillagerFactory(villagerFactory);
        selection.setVillagerAction(action);
        selection.setStrategy(strategy);
        selection.setComponentFactory(componentFactory);
        selection.setMinimumRequiredVillagers(minimumRequiredVillagers);
        selection.setMaximumRequiredVillagers(maximumRequiredVillagers);

        this.setScene(selection.show());
    }


    /**
     * Show the explore with corresponding story
     * @param story Story
     */
    public void showExplore(Story story) throws RemoteException{
        ExploreController explore = controllerFactory.createExplore();
        explore.setExploreStory(story);
        this.setScene(explore.show());
    }

    /**
     * Show the harvest selection
     * @param harvestAction HarvestAction
     * @throws RemoteException java.rmi.RemoteException
     */
    public void showHarvestSelection(HarvestAction harvestAction) throws RemoteException{
        HarvestController harvest = controllerFactory.createHarvestSelection();
        harvest.setHarvest(harvestAction);
        harvest.load();
        this.setScene(harvest.show());
    }

    /**
     * Show rewards
     * @param option Option
     * @throws RemoteException java.rmi.RemoteException
     */
    public void showRewards(Option option, ArrayList<VillagerInterface> villagers) throws RemoteException {
        RewardController rewards = controllerFactory.createRewardView();
        rewards.setRewards(option.getRewards());
        rewards.setVillagers(villagers);
        this.setScene(rewards.show());
    }

    /**
     * Show the building scene
     * @param villagers
     */
    public void showBuild(ArrayList<VillagerInterface> villagers){
        BuildController build = controllerFactory.createBuild();
        build.load();
        build.setUsedTrainerVillager(villagers);
        this.setScene(build.show());
    }

    /**
     * Show the training scene
     */
    public void showTrain() {
        TrainController train = controllerFactory.createTrain();
        this.setScene(train.show());
    }

    public void showTrainReward(MarketVillagerComponent villager) throws RemoteException {
        TrainRewardController trainReward = controllerFactory.createTrainRewardView();
        trainReward.setTrainReward(villager);
        trainReward.setVillagers(this.selectedVillagers);
        this.setScene(trainReward.show());
    }

    public void showLaborReward(ArrayList<VillagerInterface> villagers, boolean ciderReward) throws RemoteException {
        LaborRewardController laborReward = controllerFactory.createLaborRewardView();
        laborReward.setVillagers(villagers);
        laborReward.setCider(ciderReward);
        this.setScene(laborReward.show());
    }

    public void showHarvestReward(ArrayList<GoodRewardComponent> goods) throws RemoteException {
        HarvestRewardController harvestReward = controllerFactory.createHarvestRewardView();
        harvestReward.setVillagers(this.selectedVillagers);
        harvestReward.setGoodRewards(goods);
        this.setScene(harvestReward.show());
    }

    public void showBuildReward(BuildingInterface buildingReward) throws RemoteException {
        BuildRewardController buildReward = controllerFactory.createBuildRewardView();
        buildReward.setVillagers(this.selectedVillagers);
        buildReward.setRewardBuilding(buildingReward);
        this.setScene(buildReward.show());
    }

    /**
     * Show the party selection after the story
     * @param story Story
     * @param choice Choice
     */
    public void showParty(Story story, Choice choice){
        ExplorePartyController party = controllerFactory.createExploreParty();
        party.setStory(story);
        party.setChoice(choice);
        this.setScene(party.show());
    }

    public void showSellableGoods(GameClientInterface client) throws RemoteException {
        SellGoodController goods = controllerFactory.createSellGoodController();
        goods.setClient(client);

        goods.load();
        this.setScene(goods.show());

    }

    public void showBuyableGoods(GameClientInterface client) throws RemoteException {
        BuyGoodController goods = controllerFactory.createBuyGoodController();
        goods.setClient(client);

        goods.load();
        this.setScene(goods.show());
    }

    /**
     * Show the scoreboard
     */
    public void showScoreBoard(){
        ScoreboardController score = controllerFactory.createScoreBoard();
        this.setScene(score.show());
    }

    /**
     * Show the villager rest screen
     */
    public void showVillagerRest(){
        this.setScene(controllerFactory.createVillagerRest().show());
    }

    public Stage getStage(){
        return this.stage;
    }

    private void setScene(Parent root) {
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

    /**
     * Show a message on screen (This message will only show in the main view though).
     * @param message String
     */
    public void showMessage(String message) {
        this.main.showMessage(message);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setSelectedVillagers(ArrayList<VillagerInterface> selectedVillagers){
        this.selectedVillagers = selectedVillagers;
    }

}
