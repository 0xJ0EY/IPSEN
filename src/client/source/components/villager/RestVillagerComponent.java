package client.source.components.villager;

import client.source.controllers.VillagerRestController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.interfaces.VillagerInterface;

import java.io.IOException;
import java.rmi.RemoteException;

public class RestVillagerComponent extends AnchorPane {

    @FXML private Button ciderButton;
    @FXML private Button potionButton;
    @FXML private Button sleepButton;

    private VillagerInterface villager;
    private PlayerBoardControllerInterface playerBoard;

    private VillagerRestController controller;

    private boolean slept = false;

    public void setPlayerBoard(PlayerBoardControllerInterface playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void setModel(VillagerInterface villager) {
        this.villager = villager;
    }

    public void setController(VillagerRestController controller) {
        this.controller = controller;
    }

    public void load() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/rest_villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update(boolean hasCider, boolean hasPotion, boolean hasBeds) {

        try {
            this.ciderButton.setDisable(!(hasCider && this.villager.canUseCider()));
            this.potionButton.setDisable(!(hasPotion && this.villager.canUsePotion()));
            this.sleepButton.setDisable(!(hasBeds && this.villager.canSleep() && !this.slept));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void onClickCider() {
        try {
            this.villager.useCider();
            this.controller.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void onClickPotion() {
        try {
            this.villager.usePotion();
            this.controller.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void onClickSleep() {
        try {
            this.villager.sleep();
            this.slept = true;

            this.controller.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}