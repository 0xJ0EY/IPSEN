package client.source.controllers;

import client.source.Client;
import client.source.components.buy_and_sell.MarketGoodComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.GoodOnSaleInterface;
import server.sources.interfaces.MarketInterface;
import server.sources.notifications.TradeGoodNotification;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class is a controller for the view buy good.
 *
 * @author Jan Douwe Sminia
 */
public class BuyGoodController implements Serializable, ControllerInterface, MarketTradingController {
    @FXML private FlowPane goods;
    @FXML private Parent root;

    @FXML private TextField inputField;

    private int showBid = 3;
    private ArrayList<GoodOnSaleInterface> goodsOnSale;
    private ArrayList<MarketGoodComponent> goodComponents = new ArrayList<>();
    private GameClientInterface client;
    private MarketInterface market;

    /**
     * sets the market and calls update good view.
     */
    public void load(){
        try {
            this.setMarket();
            this.changeBid();
            this.updateGoodView();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Parent
     * @throws RemoteException
     */
    @Override
    public Parent show() throws RemoteException {
        return root;
    }

    /**
     * adds one coind to int bid.
     */
    @FXML
    public void addBid(){
        showBid++;
        changeBid();

    }

    /**
     * substrackts one coin from coin as long is bigger than 3.
     */
    @FXML
    public void subtracktBid(){
        if (showBid > 3){
            showBid--;

        }
        changeBid();
    }

    private void changeBid(){
        inputField.setText("" + showBid);

    }

    /**
     * @param client
     */
    public void setClient(GameClientInterface client){
        this.client = client;
    }

    /**
     * Gets the market from the server.
     *
     * @throws RemoteException
     */
    private void setMarket()throws RemoteException{
        this.market = client.getServer().getGameController().getMarket();
    }

    /**
     * Gets the goods from the market and turns them into components.
     * Adds the components to an array list and flowpane.
     *
     * @throws RemoteException
     */
    private void updateGoodView()throws RemoteException {
        this.goodsOnSale = market.getGoodList();

        if (goodsOnSale.size() > 0) {

            int index = 0;

            for (GoodOnSaleInterface goodOnSale : this.goodsOnSale) {
                MarketGoodComponent marketGoodComponent = new MarketGoodComponent();
                marketGoodComponent.setModel(goodOnSale.getGood());
                marketGoodComponent.setController(this);
                marketGoodComponent.setGoodOnSale(goodOnSale);
                marketGoodComponent.setIndex(index++);
                marketGoodComponent.load();

                goodComponents.add(marketGoodComponent);
                goods.getChildren().add(marketGoodComponent);
            }
        } else {
            System.out.println("Geen goods on sale");
        }
    }

    /**
     * Checks every component if the boolean selected is true.
     * Adds them to an Arraylist and returns them.
     */
    @Override
    public ArrayList<MarketGoodComponent> getSelectedGoods() {
        ArrayList<MarketGoodComponent> selected = new ArrayList<MarketGoodComponent>();

        for (MarketGoodComponent goodComponent : this.goodComponents) {
            if (goodComponent.isSelected()) {
                selected.add(goodComponent);
            }
        }

        return selected;
    }

    /**
     * Gets the selected good and sends notification to their original owners.
     *
     * @throws RemoteException
     */
    @FXML
    public void confirmSelection() throws RemoteException{
        for (MarketGoodComponent goodComponent : this.goodComponents) {
            if (goodComponent.isSelected()){
                goodComponent.getGoodOnSale().getClient().receiveNotification(
                        new TradeGoodNotification(this.showBid, goodComponent.getGoodOnSale(), goodComponent.getIndex(), this.client)
                );
            }
        }

        this.client.getClient().showMain();
    }

    @FXML
    private void cancelAction() throws RemoteException{
        this.client.getClient().showMain();
    }
}
