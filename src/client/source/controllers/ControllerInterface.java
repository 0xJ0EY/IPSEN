package client.source.controllers;

import client.source.components.buy_and_sell.MarketGoodComponent;
import javafx.scene.Parent;
import java.rmi.RemoteException;

public interface ControllerInterface {

    public Parent show() throws RemoteException;
}
