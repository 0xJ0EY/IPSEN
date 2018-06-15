package client.source.controllers;

import client.source.Client;
import client.source.components.villager.RestVillagerComponent;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import server.sources.actions.RestVillagerAction;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VillagerRestController implements ControllerInterface, Observable {

    @FXML Parent root;

    @FXML private FlowPane villagerContainer;

    @FXML private Text bedsText;
    @FXML private Text ciderText;
    @FXML private Text potionsText;

    private Client client;

    private PlayerBoardInterface playerBoard;
    private ArrayList<VillagerInterface> villagers;

    private ArrayList<RestVillagerComponent> villagerComponents;

    @Override
    public Parent show() {

        this.client.playerBoardObserver.attach(this);
        this.updateObserver();

        this.fetchVillagers();

        this.load();

        return this.root;
    }

    /**
     * Fetching villagers on playerboard.
     * @author Joey de Ruiter
     */
    public void fetchVillagers() {
        try {
            this.villagers = this.playerBoard.listVillagers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all villagers components in container.
     * @author Joey de Ruiter
     */
    public void load() {
        this.villagerComponents = new ArrayList<RestVillagerComponent>();
        villagerContainer.getChildren().clear();

        for (VillagerInterface villager : this.villagers) {
            RestVillagerComponent villagerComponent = new RestVillagerComponent();

            villagerComponent.setPlayerBoard(this.playerBoard);
            villagerComponent.setModel(villager);
            villagerComponent.setController(this);
            villagerComponent.load();

            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }

        this.update();
    }

    /**
     * For updating villager rest components
     * @author Joey de Ruiter
     */
    public void update() {

        try {
            boolean hasCider = this.playerBoard.hasCider();
            boolean hasPotion = this.playerBoard.hasPotion();
            boolean hasBeds = this.playerBoard.hasBeds();

            for (RestVillagerComponent villagerComponent : villagerComponents) {
                villagerComponent.update(hasCider, hasPotion, hasBeds);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * For setting a client.
     * @param client
     * @author Joey de Ruiter
     */
    public void setClient(Client client) {
        try {
            this.client = client;
            this.playerBoard = client.getGameClient().getPlayer().getPlayerBoard();

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }

    /**
     * For selecting villagers to replenish
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    @FXML private void onClickSelect() throws RemoteException {

        this.client.getGameClient().getPlayer().doAction(new RestVillagerAction());
        this.client.showMain();

    }

    @Override
    public void updateObserver() {
        PlayerBoardInterface playerboard = this.client.playerBoardObserver.getState();

        try {
            int beds = playerboard.getBeds();
            int ciders = playerboard.getCiders();
            int potions = playerboard.getPotions();

            String bedsString = String.format(beds == 1 ? "%s bed" : "%s beds", beds);
            String cidersString = String.format(ciders == 1 ? "%s cider" : "%s ciders", ciders);
            String potionsString = String.format(potions == 1 ? "%s potion" : "%s potions", potions);

            this.bedsText.setText(bedsString);
            this.ciderText.setText(cidersString);
            this.potionsText.setText(potionsString);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void keys() {
        root.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            switch (keyCode) {
                case D:
                    try {
                        this.onClickSelect();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    break;
            }
        });
    }
}
