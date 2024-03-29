package client.source.components.villager;

import client.source.controllers.VillagerRestController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * This class allows to set tired villagers components in exhausted place
 * @author Richard Kerkvliet
 */
public class RestVillagerComponent extends VillagerComponent {

    @FXML private Button ciderButton;
    @FXML private Button potionButton;
    @FXML private Button sleepButton;

    private PlayerBoardInterface playerBoard;
    private VillagerRestController controller;

    private boolean slept = false;

    @Override
    protected void loadView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/rest_villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * For setting a playerboard.
     * @author Richard Kerkvliet
     */
    public void setPlayerBoard(PlayerBoardInterface playerBoard) {
        this.playerBoard = playerBoard;
    }

    /**
     * For setting a controller.
     * @author Richard Kerkvliet
     */
    public void setController(VillagerRestController controller) {
        this.controller = controller;
    }

    /**
     * For clicking on cider good to replenish villagers
     * @author Richard Kerkvliet
     */
    @FXML public void onClickCider() {
        try {
            this.villager.useCider();
            this.controller.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * For clicking on potion good to replenish villagers
     * @author Richard Kerkvliet
     */
    @FXML public void onClickPotion() {
        try {
            this.villager.usePotion();
            this.controller.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * For clicking on sleep to replenish villagers
     * @author Richard Kerkvliet
     */
    @FXML public void onClickSleep() {
        try {
            this.villager.sleep();
            this.slept = true;

            this.controller.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * For updating goods during replenishing villagers
     * @author Richard Kerkvliet
     */
    public void update(boolean hasCider, boolean hasPotion, boolean hasBeds) {
        try {
            this.ciderButton.setDisable(!(hasCider && this.villager.canUseCider()));
            this.potionButton.setDisable(!(hasPotion && this.villager.canUsePotion()));
            this.sleepButton.setDisable(!(hasBeds && this.villager.canSleep() && !this.slept));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
