package client.source.components.villager_to_train;

import client.source.components.villager.SingleSelectableVillagerComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;

public class TrainerVillagerComponent extends SingleSelectableVillagerComponent {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/villager_to_train.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
