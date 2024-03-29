package client.source.factories;

import client.source.Client;
import client.source.controllers.*;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * A Class that allows to create controllers
 * Created by Joey de Ruiter
 */
public class ControllerFactory {

    private Client client;

    public ControllerFactory(Client client) {
        this.client = client;
    }

    /**
     * Load the view and controller of login
     * @return the assinged controller
     */
    public LoginController createLogin() {
        LoginController login = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/login.fxml"));
            loader.load();

            login = loader.getController();
            login.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return login;
    }

    /**
     * Load the view and controller of lobby
     * @return the assigned controller
     */
    public LobbyController createLobby() {
        LobbyController lobby = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/lobby.fxml"));
            loader.load();

            lobby = loader.getController();
            lobby.registerClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lobby;
    }

    /**
     * Load the view and controller of main
     * @return the assinged controller
     */
    public MainController createMain() {
        MainController main = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/main.fxml"));
            loader.load();

            main = loader.getController();
            main.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return main;
    }

    /**
     * Load the view and controller of expore
     * @return the assinged controller
     */
    public ExploreController createExplore() {
        ExploreController explore = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/explore.fxml"));
            loader.load();

            explore = loader.getController();
            explore.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return explore;
    }

    /**
     * For loading a reward view after a succesfull exploration.
     * @return train reward view
     * @author Richard Kerkvliet
     */
    public RewardController createRewardView(){
        RewardController reward = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/reward.fxml"));
            loader.load();

            reward = loader.getController();
            reward.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reward;
    }

    /**
     * For loading a train reward view after recruiting a villager.
     * @return train reward view
     * @author Richard Kerkvliet
     */
    public TrainRewardController createTrainRewardView(){
        TrainRewardController trainRewardController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/trainReward.fxml"));
            loader.load();

            trainRewardController = loader.getController();
            trainRewardController.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trainRewardController;
    }

    public LaborRewardController createLaborRewardView(){
        LaborRewardController laborRewardController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/labor_reward.fxml"));
            loader.load();

            laborRewardController = loader.getController();
            laborRewardController.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return laborRewardController;
    }

    public HarvestRewardController createHarvestRewardView(){
        HarvestRewardController harvestRewardController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/harvest_reward.fxml"));
            loader.load();

            harvestRewardController = loader.getController();
            harvestRewardController.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return harvestRewardController;
    }

    public BuildRewardController createBuildRewardView() {
        BuildRewardController buildRewardController = null;

        try{
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/client/resources/views/build_reward.fxml")));
            loader.load();

            buildRewardController = loader.getController();
            buildRewardController.setClient(this.client);
        } catch (IOException e){
            e.printStackTrace();
        }

        return buildRewardController;
    }

    /**
     * Load the view and controller of build
     * @return the assinged controller
     * @author Robin Silverio
     */
    public BuildController createBuild() {
        BuildController build = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/build_market.fxml"));
            loader.load();

            build = loader.getController();
            build.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return build;
    }

    /**
     * For loading a train market view.
     * @return train market view
     * @author Joey de Ruiter
     */
    public TrainController createTrain() {
        TrainController trainController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/train_market.fxml"));
            loader.load();

            trainController = loader.getController();
            trainController.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trainController;
    }

    /**
     * Load the view and controller of villager selection
     * @return the assinged controller
     * @author Joey de Ruiter
     */
    public VillagerSelectionController createVillagerSelection() {
        VillagerSelectionController villagerSelection = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/villager_selection.fxml"));
            loader.load();

            villagerSelection = loader.getController();
            villagerSelection.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return villagerSelection;
    }

    /**
     * For loading harvest view.
     * @return harvest view
     * @author Jan Douwe Sminia
     */
    public HarvestController createHarvestSelection(){
        HarvestController harvestSelection = null;

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/harvest.fxml"));
            loader.load();

            harvestSelection = loader.getController();
            harvestSelection.setClient(this.client);
        } catch (IOException e){
            e.printStackTrace();
        }

        return  harvestSelection;
    }

    /**
     * For loading an explore party view
     * @return explore party view
     * @author Richard Kerkvliet
     */
    public ExplorePartyController createExploreParty() {
        ExplorePartyController explorePartyController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/party_roll_dice.fxml"));
            loader.load();

            explorePartyController = loader.getController();
            explorePartyController.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return explorePartyController;
    }

    /**
     * For loading a scoreboard view.
     * @return scoreboard view
     * @author Robin Silverio
     */
    public ScoreboardController createScoreBoard(){
        ScoreboardController scoreboardController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/scoreboard.fxml"));
            loader.load();

            scoreboardController = loader.getController();
            scoreboardController.setClient(this.client);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return scoreboardController;
    }

    /**
     * For loading a villager rest view
     * @return villager rest view
     * @author Richard Kerkvliet
     */
    public VillagerRestController createVillagerRest() {
        VillagerRestController villagerRestController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/villager_resting.fxml"));
            loader.load();

            villagerRestController = loader.getController();
            villagerRestController.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return villagerRestController;
    }

    public SellGoodController createSellGoodController(){
        SellGoodController sellGoodController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/sell_good.fxml"));
            loader.load();

            sellGoodController = loader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }

        return sellGoodController;
    }

    public BuyGoodController createBuyGoodController(){
        BuyGoodController buyGoodController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/buy_good.fxml"));
            loader.load();

            buyGoodController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buyGoodController;
    }

}
