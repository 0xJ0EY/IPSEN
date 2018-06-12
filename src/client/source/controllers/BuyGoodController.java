package client.source.controllers;

import client.source.Client;
import client.source.components.buy_and_sell.MarketGoodComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.controllers.GoodOnSale;
import server.sources.models.Market;
import server.sources.models.goods.Good;

import java.awt.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class BuyGoodInterfaceController implements Serializable, ControllerInterface, MarketTradingController {
    @FXML private FlowPane goodPane;
    @FXML private Parent root;
    @FXML private TextField bid;

    private int showBid = 3;
    private ArrayList<GoodOnSale> goodsOnSale;
    private ArrayList<MarketGoodComponent> goodComponents = new ArrayList<>();
    private Client client;
    private Market market;

    @Override
    public Parent show() throws RemoteException {
        return null;
    }

    @FXML
    public void confirmSelection() throws RemoteException{
        for (int i = 0; i < goodsOnSale.size(); i++){
            if(goodComponents.get(i).isSelected()){
                this.client.showBid(goodsOnSale.get(i), this.showBid);
                if(goodsOnSale.get(i).getStatus()){
                    this.bidAccepted(goodsOnSale.get(i).buyGood());
                    this.goodsOnSale.remove(i);

                } else {
                    this.bidDeclined();

                }
            }
        }
    }

    @FXML
    public void addBid(){
        showBid++;
        changeBid();

    }

    @FXML
    public void subtracktBid(){
        if (showBid > 3){
            showBid--;

        }
        changeBid();

    }

    private void changeBid(){
        bid.setText("" + showBid);

    }

    public void setClient(Client client){
        this.client = client;
        this.market = market;
        this.setGoodPane();

    }

    public void setGoodPane(){
        this.goodsOnSale = market.getGoodList();

        for (int i = 0; i < goodsOnSale.size(); i++){
            goodComponents.add(new MarketGoodComponent(this, goodsOnSale.get(i).getGoodComponent()));
            goodPane.getChildren().add(goodComponents.get(i));

        }
    }


    @Override
    public void selectGood(MarketGoodComponent good){
        for (int i = 0; i < goodComponents.size(); i++){
            goodComponents.get(i).setFalse();

        }

        good.setTrue();
    }

    private void bidAccepted(Good good) throws RemoteException{
        this.client.getGameClient().getPlayer().getPlayerBoard().getGoods().add(good);


    }

    private void bidDeclined(){


    }

}
