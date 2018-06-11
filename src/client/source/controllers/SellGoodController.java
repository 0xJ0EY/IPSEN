package client.source.controllers;

import client.source.Client;
import client.source.components.buy_and_sell.MarketGoodComponent;
import client.source.components.harvest.BackgroundComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.controllers.GoodOnSale;
import server.sources.interfaces.MarketInterface;
import server.sources.models.Market;
import server.sources.models.goods.Good;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SellGoodController implements Serializable, ControllerInterface, OnlyOneTrueInterface{
    @FXML private Parent root;
    @FXML private FlowPane goods;

    private Client client;
    private MarketInterface market;

    private ArrayList<Good> availableGoods;
    private ArrayList<MarketGoodComponent> goodComponents = new ArrayList<>();


    @Override
    public Parent show() throws RemoteException {
        this.updateGoodView();

        return root;

    }

    private void updateGoodView() throws RemoteException{
        getPlayerGoods();

        for (int i = 0; i < availableGoods.size(); i++) {
            goodComponents.add(new MarketGoodComponent(this, availableGoods.get(i)));
            goods.getChildren().add(goodComponents.get(i));
        }
    }

    private void getPlayerGoods() throws RemoteException{
        availableGoods =  client.getGameClient().getPlayer().getPlayerBoard().getGoods();
    }

    public void setClient(Client client) throws RemoteException{
        this.client = client;
        this.market = client.getGameClient().getServer().getGameController().getMarket();

    }

    @Override
    public void onlyOneTrue(MarketGoodComponent good){
        for (int i = 0; i < goodComponents.size(); i++){
            goodComponents.get(i).setFalse();
        }

        good.setTrue();
    }

    @FXML
    public void confirmSelection() throws RemoteException{
        for (int i = 0; i < goodComponents.size(); i++){
            if (goodComponents.get(i).isSelected()){
                market.sellGood(goodComponents.get(i).getGood(), client);

            }
        }
    }
}
