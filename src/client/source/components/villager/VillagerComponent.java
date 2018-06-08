package client.source.components.villager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;

import java.io.IOException;
import java.rmi.RemoteException;

public class VillagerComponent extends AnchorPane {

    protected VillagerInterface villager;

    @FXML protected AnchorPane background;

    @FXML protected Text labelType;

    @FXML protected AnchorPane type;


    public VillagerComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setModel(VillagerInterface villager) {
        this.villager = villager;
    }

    public void load()  {
        try {
            this.type.getChildren().setAll(villager.getType());

            this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/villagerBackgrounds/" + this.villager.getBackground() + " ');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 110 200"
            );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public VillagerInterface getVillager() {
        return villager;
    }
}
