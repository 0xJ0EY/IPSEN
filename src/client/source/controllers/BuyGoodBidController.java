package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import server.sources.controllers.GoodOnSale;

import java.awt.*;
import java.rmi.RemoteException;

public class BuyGoodBidController implements ControllerInterface {
    @FXML private TextField bid;
    @FXML private AnchorPane good;
    @FXML private Parent root;

    private int showBid;
    private GoodOnSale goodOnSale;
    private Client client;

    @Override
    public Parent show() throws RemoteException {
        return root;
    }

    @FXML
    public void acceptBid(){
        goodOnSale.setStatusTrue();

    }

    @FXML
    public void declineBid(){
        goodOnSale.setStatusFalse();

    }

    public void setBid(int bid, GoodOnSale goodOnSale){
        this.showBid = bid;
        this.goodOnSale = goodOnSale;

    }

    public void setClient(Client client){
        this.client = client;

    }

}
