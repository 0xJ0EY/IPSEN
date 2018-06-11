package server.sources.interfaces;

import client.source.Client;
import server.sources.controllers.GoodOnSale;
import server.sources.models.goods.Good;

import java.rmi.Remote;
import java.util.ArrayList;

public interface MarketInterface extends Remote {
    public void sellGood(Good good, Client target);

    public ArrayList<GoodOnSale> getGoodList();
}
