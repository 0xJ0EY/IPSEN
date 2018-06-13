package server.sources.controllers;

import client.source.Client;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.GameClientInterface;
import server.sources.models.goods.Good;

public class GoodOnSale {
    private Client target;
    private Good goodOnSale;
    private boolean statusBid;

    public GoodOnSale(Client target, Good GoodOnSale){
        this.goodOnSale = GoodOnSale;
        this.target = target;

    }

    public Client getTarget(){
        return target;

    }

    public Good buyGood(){
        return goodOnSale;

    }

    public Good getGoodComponent(){
        return goodOnSale;

    }

    public void setStatusTrue(){
        this.statusBid = true;

    }

    public void setStatusFalse(){
        this.statusBid = false;

    }

    public boolean getStatus(){
        return this.statusBid;

    }

    public Client getClient(){
        return target;
    }
}

