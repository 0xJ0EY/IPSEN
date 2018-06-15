package client.source.components.villager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

import java.io.IOException;

public class MarketVillagerComponent extends VillagerComponent {

    private int price = 0;

    @FXML private Text priceText;

    @Override
    protected void loadView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/market_villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;

        this.priceText.setText(Integer.toString(this.price));
    }


}
