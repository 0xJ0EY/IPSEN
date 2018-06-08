package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SellGoodController implements ControllerInterface{
    @FXML private Parent root;
    @FXML private FlowPane goods;

    private Client client;

    private ArrayList<Good> availableGoods;


    @Override
    public Parent show() throws RemoteException {
        this.updateGoodView();

        return root;

    }

    private void updateGoodView() throws RemoteException{
        getPlayerGoods();

        for (int i = 0; i < availableGoods.size(); i++) {
            goods.getChildren().add(availableGoods.get(i).getGood());
        }
    }

    private void getPlayerGoods() throws RemoteException{
        availableGoods =  client.getGameClient().getPlayer().getPlayerBoard().getGoods();
    }
}
