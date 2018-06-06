package client.source.components.villager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.io.IOException;
import java.rmi.RemoteException;

public class RestVillagerComponent extends AnchorPane {

    private VillagerInterface villager;
    private PlayerBoardControllerInterface playerBoard;

    private boolean slept = false;

    @FXML private Button ciderButton;
    @FXML private Button potionButton;
    @FXML private Button sleepButton;

    public void setPlayerBoard(PlayerBoardControllerInterface playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void setModel(VillagerInterface villager) {
        this.villager = villager;
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

        this.update();
    }

    public void update() {
        try {
            boolean hasCider = this.playerBoard.hasCider();
            boolean hasPotion = this.playerBoard.hasPotion();
            boolean hasBeds = this.playerBoard.hasBeds();

            this.ciderButton.setDisable(!(hasCider && this.villager.canUseCider()));
            this.potionButton.setDisable(!(hasPotion && this.villager.canUsePotion()));
            this.sleepButton.setDisable(!(hasBeds && this.villager.canSleep() && !this.slept));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void onClickCider() {
        try {
            this.playerBoard.useCider(this.villager);
            this.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void onClickPotion() {
        try {
            this.playerBoard.usePotion(this.villager);
            this.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void onClickSleep() {
        System.out.println("Sleep");

        this.slept = true;
        this.update();
    }

}
