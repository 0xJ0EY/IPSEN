package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import server.sources.actions.RefreshHousesAction;
import server.sources.models.GameClient;

import java.rmi.RemoteException;

public class MarketController {

    @FXML private Parent root;


    private Client client;

    /**
     * For setting a client
     *
     * @param client
     */
    public void registerClient(Client client) {
        this.client = client;
    }

    public void clickRefreshHouses() {

        try {
            client.getGameClient().requestAction(new RefreshHousesAction());

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void sellGood() throws RemoteException{
        this.client.showSellableGoods(client.getGameClient());

    }

    @FXML
    public void buyGood() throws RemoteException{
        this.client.showBuyableGoods(client.getGameClient());

    }

}