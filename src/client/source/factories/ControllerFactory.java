package client.source.factories;

import client.source.Client;
import client.source.controllers.ExploreController;
import client.source.controllers.LobbyController;
import client.source.controllers.LoginController;
import client.source.controllers.MainController;
import client.source.controllers.VillagerSelectionController;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/views/login.fxml"));
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
     * @return the assinged controller
     */
    public LobbyController createLobby() {
        LobbyController lobby = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/views/lobby.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/views/main.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/views/explore.fxml"));
            loader.load();

            explore = loader.getController();
            explore.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return explore;
    }

    /**
     * Load the view and controller of villager selection
     * @return the assinged controller
     */
    public VillagerSelectionController createVillagerSelection() {
        VillagerSelectionController villagerSelection = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/views/villager_selection.fxml"));
            loader.load();

            villagerSelection = loader.getController();
            villagerSelection.setClient(this.client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return villagerSelection;
    }

}
