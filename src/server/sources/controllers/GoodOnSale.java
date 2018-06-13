package server.sources.controllers;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.GoodOnSaleInterface;
import server.sources.models.goods.Good;

import java.io.Serializable;

public class GoodOnSale implements GoodOnSaleInterface {
    private GameClientInterface target;
    private Good goodOnSale;

    public GoodOnSale(GameClientInterface target, Good GoodOnSale){
        this.goodOnSale = GoodOnSale;
        this.target = target;

    }

    public GameClientInterface getTarget(){
        return target;

    }

    public Good getGood() {
        return goodOnSale;

    }

    public GameClientInterface getClient(){
        return target;
    }
}

