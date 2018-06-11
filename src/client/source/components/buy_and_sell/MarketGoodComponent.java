package client.source.components.buy_and_sell;

import client.source.controllers.ControllerInterface;
import client.source.controllers.OnlyOneTrueInterface;
import client.source.controllers.SellGoodController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import server.sources.models.goods.Good;

import java.io.IOException;
import java.io.Serializable;

public class MarketGoodComponent extends AnchorPane implements Serializable {
    @FXML private AnchorPane background;
    private Boolean selected = false;

    private OnlyOneTrueInterface controller;
    private Good good;

    public MarketGoodComponent(OnlyOneTrueInterface controller, Good good){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/sell_and_buy/marketGood.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.controller = controller;
        this.good = good;

        background.setStyle("-fx-background-image: " + good.getBackground() + "");
    }

    @FXML
    public void selectedGood(){
        controller.onlyOneTrue(this);
    }

    public void setFalse(){
        this.selected = false;
    }

    public void setTrue(){
        this.selected = true;
    }

    public boolean isSelected(){
        return selected;
    }

    public Good getGood(){
        return good;
    }

}
