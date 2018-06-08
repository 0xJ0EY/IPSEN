package client.source.components.villager_to_train;

import client.source.Client;
import client.source.components.villager.SelectableVillagerComponent;
import client.source.components.villager.VillagerComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.AllroundVillager;
import server.sources.models.villagers.TrainerVillager;
import server.sources.models.villagers.Villager;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class TrainerVillagerComponent extends SelectableVillagerComponent {

    @FXML
    public void onClickSelect(ArrayList<TrainerVillagerComponent> villagerComponents) {
        for (TrainerVillagerComponent villager : villagerComponents) {
            villager.selected = false;
            villager.hideIndicator();
        }
        this.selected = true;
        this.showIndicator();
    }

    public TrainerVillagerComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager_to_train/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
