package client.source.controllers;

import client.source.Client;
import client.source.components.buy_and_sell.MarketGoodComponent;
import client.source.components.good.GoodComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.MarketInterface;
import server.sources.models.goods.Good;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This Class is a controller for the sell good view.
 *
 * @author Jan Douwe Sminia
 */
public class SellGoodController implements Serializable, MarketTradingController {
    @FXML private Parent root;
    @FXML private FlowPane goods;

    private GameClientInterface client;
    private MarketInterface market;

    private ArrayList<Good> availableGoods;
    private ArrayList<MarketGoodComponent> goodComponents = new ArrayList<>();

    /**
     * gets the player goods and makes components from them adding them to de flowpane.
     */
    public void load() {
        try {
            this.getPlayerGoods();
            this.updateGoodView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    @Override
    public Parent show()  {
        return root;
    }

    /**
     * Gets the goods from the client.
     *
     * @throws RemoteException
     */
    private void getPlayerGoods() throws RemoteException{
        this.availableGoods = client.getPlayer().getPlayerBoard().getGoods();
    }

    /**
     * makes components form the goods and adds them to an array list and the flowpane.
     */
    private void updateGoodView() {

        int index = 0;

        for (Good availableGood : this.availableGoods) {
            MarketGoodComponent marketGoodComponent = new MarketGoodComponent();
            marketGoodComponent.setModel(availableGood);
            marketGoodComponent.setController(this);
            marketGoodComponent.setIndex(index++);
            marketGoodComponent.load();

            goodComponents.add(marketGoodComponent);
            goods.getChildren().add(marketGoodComponent);
        }
    }

    /**
     * sets the client and gets the market from the server.
     *
     * @param client
     * @throws RemoteException
     */
    public void setClient(GameClientInterface client) throws RemoteException{
        this.client = client;
        this.market = client.getServer().getGameController().getMarket();

    }

    /**
     * this method gets every component where the boolean selected is true and adds them to an array list.
     *
     * @return Array List
     */
    @Override
    public ArrayList<MarketGoodComponent> getSelectedGoods() {
        ArrayList<MarketGoodComponent> selected = new ArrayList<>();

        for (MarketGoodComponent goodComponent : this.goodComponents) {
            if (goodComponent.isSelected()) {
                selected.add(goodComponent);
            }
        }

        return selected;
    }


    /**
     * The selected goods get sold to the market together with the client
     * @throws RemoteException
     */
    @FXML
    public void confirmSelection() throws RemoteException{
        for (MarketGoodComponent goodComponent : this.goodComponents) {
            if (goodComponent.isSelected()) {
                market.sellGood(goodComponent.getGood(), client);
                this.client.getPlayer().getPlayerBoard().goodSold(goodComponent.getIndex());

            }
        }
        this.client.getClient().showMain();

    }

    @FXML
    private void cancelAction() throws RemoteException{
        this.client.getClient().showMain();
    }
}
