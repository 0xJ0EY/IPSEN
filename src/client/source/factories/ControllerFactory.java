package client.source.factories;

import client.source.Client;
import client.source.components.building.HouseComponent;
import client.source.components.building.KeyhouseComponent;
import client.source.components.building.OutpostComponent;
import client.source.components.building.StarhouseComponent;
import client.source.components.villager_to_train.TrainerVillagerComponent;
import client.source.controllers.*;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

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
     * Load the view and controller of build
     * @return the assinged controller
     * @author Robin Silvério
     */
    public BuildController createBuild() {
        BuildController build = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/build_market.fxml"));
            loader.load();

            build = loader.getController();
            build.setClient(this.client);
            HouseComponent.setClient(this.client);
            StarhouseComponent.setClient(this.client);
            KeyhouseComponent.setClient(this.client);
            OutpostComponent.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return build;
    }

    public TrainController createTrain() {
        TrainController trainController = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/train_market.fxml"));
            loader.load();

            trainController = loader.getController();
            trainController.setClient(this.client);
            TrainerVillagerComponent.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trainController;
    }

    /**
     * Load the view and controller of villager selection
     * @return the assinged controller
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

}
