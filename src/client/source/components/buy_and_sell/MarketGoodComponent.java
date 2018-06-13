package client.source.components.buy_and_sell;

import client.source.components.good.GoodComponent;
import client.source.controllers.MarketTradingController;
import client.source.controllers.SellGoodController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import server.sources.controllers.GoodOnSale;
import server.sources.interfaces.GoodOnSaleInterface;
import server.sources.models.goods.Good;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class is an component that can be loaded into a controller.
 *
 * @author Jan Douwe Sminia
 */
public class MarketGoodComponent extends GoodComponent implements Serializable {

    private boolean selected = false;
    private MarketTradingController controller;
    private GoodOnSaleInterface goodOnSale;
    private int index;

    /**
     * Loads a fxml file.
     */
    public MarketGoodComponent(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/good/market_good.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the style for the component.
     */
    @Override
    public void load()  {
        this.deselect();
    }

    /**
     * This method makes sure only one component can be selected at once.
     */
    @FXML
    protected void onClickSelect() {
        this.deselectGoods();

        this.select();

        System.out.println("Toggle");
    }

    /**
     * Gets every selected component and deselects them.
     */
    private void deselectGoods() {
        for (MarketGoodComponent marketGoodComponent : this.controller.getSelectedGoods()) {
            marketGoodComponent.deselect();
        }
    }

    /**
     * @param controller
     */
    public void setController(MarketTradingController controller) {
        this.controller = controller;
    }

    /**
     * @param goodOnSale
     */
    public void setGoodOnSale(GoodOnSaleInterface goodOnSale){
        this.goodOnSale = goodOnSale;
    }

    public void setIndex(int index){
        this.index = index;
    }

    /**s
     * @return
     */
    public GoodOnSaleInterface getGoodOnSale(){
        return goodOnSale;
    }

    /**
     * @return
     */
    public boolean isSelected(){
        return selected;
    }

    /**
     * Sets selected true and makes and marks the selected component.
     */
    public void select() {
        this.selected = true;
        this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/goods/" + this.good.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 50 50; " +
                "-fx-effect: dropshadow(one-pass-box, green, 10, 0, 0, 0);" +
                "-fx-padding: 10 10 10 10"
        );

    }

    /**
     * Sets selected to false and turns the component to 'normal'.
     */
    public void deselect() {
        this.selected = false;
        this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/goods/" + this.good.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 50 50; " +
                "-fx-effect: dropshadow(one-pass-box, white, 10, 0, 0, 0);" +
                "-fx-padding: 10 10 10 10"
        );
    }

    /**
     * @return
     */
    public Good getGood(){
        return good;
    }

    public int getIndex(){
        return this.index;
    }
}
