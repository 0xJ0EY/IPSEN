package client.source.controllers;

import client.source.components.buy_and_sell.MarketGoodComponent;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MarketTradingController extends ControllerInterface {

    public ArrayList<MarketGoodComponent> getSelectedGoods();

}
