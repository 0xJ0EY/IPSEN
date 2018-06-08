package server.sources.controllers;

import client.source.Client;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.GameClientInterface;
import server.sources.models.goods.Good;

public class GoodOnSale {
    private GameClientInterface target;
    private Good goodOnSale;

    public GoodOnSale(GameClientInterface target, Good GoodOnSale){
        this.goodOnSale = GoodOnSale;
        this.target = target;

    }

    public GameClientInterface getTarget(){
        return target;

    }

    public Good buyGood(){
        return goodOnSale;

    }

    public AnchorPane getGoodComponent(){
        return goodOnSale.getGood();

    }
}

