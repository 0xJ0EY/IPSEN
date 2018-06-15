package client.source.controllers;

import client.source.Client;
import client.source.components.villager.SelectableVillagerComponent;
import client.source.components.villager.VillagerComponent;
import client.source.components.villager_to_train.TrainerVillagerComponent;
import client.source.observers.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import server.sources.interfaces.MarketInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A class that acts as an intermediary between train view and models.
 * Created by robin on 7-6-2018.
 */
public class TrainController implements VillagerSelectionControllerInterface {

    private final int minimumRequiredVillagers = 1;
    private final int basePrice = 2;

    private Client client;
    private MarketInterface market;
    @FXML private Parent root;

    @FXML private GridPane villagerContainer;

    @FXML private Button cancelButton;

    @FXML private Button buyButton;

    @FXML private Text message;

    private VillagerInterface[] availableVillagers;

    private ArrayList<TrainerVillagerComponent> villagerComponents;
    private PlayerInterface target;

    private Thread messageThread;

    /**
     * Loads available villagers to be recruited and trained
     * @return available villager units
     * @author Robin Silverio
     */
    @Override
    public Parent show() {

        try {
            this.target = client.turnObserver.getState();

            this.updateButtons();

            this.retrieveVillagers();

            this.updateVillagersView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return this.root;
    }

    /**
     * For getting all villagers
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public void retrieveVillagers() throws RemoteException {
        this.availableVillagers = market.listAvailableVillagers();
    }

    /**
     * For updating all villagers to its containers.
     * @author Joey de Ruiter and Richard kerkvliet
     */
    private void updateVillagersView() {
        this.villagerComponents = new ArrayList<TrainerVillagerComponent>();
        this.villagerContainer.getChildren().clear();

        for (int i = 0; i < this.availableVillagers.length; i++) {
            if (this.availableVillagers[i] == null) continue;

            TrainerVillagerComponent villagerComponent = new TrainerVillagerComponent();
            villagerComponent.setModel(this.availableVillagers[i]);
            villagerComponent.setPrice(this.basePrice + i);
            villagerComponent.setController(this);
            villagerComponent.load();

            this.villagerComponents.add(villagerComponent);

            GridPane.setColumnIndex(villagerComponent, i);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }

    /**
     * Setting a client in trainview
     * @param client
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio and Richard Kerkvliet (for correcting code)
     */
    public void setClient(Client client) throws RemoteException {
        this.client = client;

        this.market = client.getGameClient().getServer().getGameController().getMarket();

    }

    /**
     * Allowes user to train chosen villager
     * @throws RemoteException java.rmi.RemoteException
     * @author Richard Kerkvliet
     */
    public void onClickTrain() throws RemoteException {
        for(int i=0; i < villagerComponents.size(); i++){
            TrainerVillagerComponent villager = villagerComponents.get(i);

            if(villager.isSelected()){
                market.buyRemoteVillager(client.getGameClient(), villager.getVillager());

                this.client.getGameClient()
                    .getClient()
                    .getGameClient()
                    .getPlayer()
                    .getPlayerBoard()
                    .payCoin(villager.getPrice())
                ;

                this.availableVillagers[i] = null;
                client.showTrainReward(villager);
            }

        }
    }

    public void onClickCancel() throws RemoteException{
        client.showMain();
    }

    public void updateButtons() {
        boolean turn = this.hasTurn();
        this.cancelButton.setDisable(!turn);
        this.buyButton.setDisable(true);

    }

    @Override
    public ArrayList<SelectableVillagerComponent> getSelectedVillagerComponents() {
        ArrayList<SelectableVillagerComponent> villagers = new ArrayList<SelectableVillagerComponent>();

        for (SelectableVillagerComponent villagerComponent : this.villagerComponents) {
            if (villagerComponent.isSelected()) villagers.add(villagerComponent);
        }

        return villagers;
    }

    @Override
    public void showMessage(String message) {
        if (this.messageThread != null && this.messageThread.isAlive()) this.messageThread.interrupt();

        Runnable r = () -> {
            this.message.setText(message);
            this.message.setVisible(true);

            try {
                Thread.sleep(1710);
            } catch (InterruptedException e) {
                System.out.println("Message interrupted");
            } finally {
                this.message.setVisible(false);
            }
        };

        this.messageThread = new Thread(r);
        this.messageThread.start();
    }

    @Override
    public void update() {
        int villagerSelection = this.getSelectedVillagerComponents().size();

        // Disable the button when there are less villagers selected
        this.buyButton.setDisable(villagerSelection < this.minimumRequiredVillagers);
    }

    @Override
    public boolean hasTurn() {
        try {
            return this.target.getGameClient().equals(client.getGameClient());
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client getClient() {
        return this.client;
    }
}
