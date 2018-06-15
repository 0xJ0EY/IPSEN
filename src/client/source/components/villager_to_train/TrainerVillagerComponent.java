package client.source.components.villager_to_train;

import client.source.components.villager.SingleSelectableVillagerComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TrainerVillagerComponent extends SingleSelectableVillagerComponent {

    @FXML private Label price_train_label;
    private int price;

    /**
     * For selecting trainer villagers
     * @param villagerComponents
     * @author Robin Silverio
     */
    @FXML
    public void onClickSelect(ArrayList<TrainerVillagerComponent> villagerComponents) {
        for (TrainerVillagerComponent villager : villagerComponents) {
            villager.selected = false;
            villager.hideIndicator();
        }
        this.selected = true;
        this.showIndicator();
    }

    @Override
    protected void loadView() {

        Random rnd = new Random(); // This is only necessary to generate price.

        this.price = rnd.nextInt(10);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/villager_to_train.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            price_train_label.setText("Price: " + this.price);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPrice() {
        return price;
    }
}
