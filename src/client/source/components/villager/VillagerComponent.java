package client.source.components.villager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Buildable;
import server.sources.models.villagers.BuilderVillager;
import server.sources.models.villagers.Trainable;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * This class allows to create common villager components
 * @author Robin Silvério
 */
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
            this.setType();

            this.background.setStyle(
                "-fx-background-image: url('/client/resources/img/villager_backgrounds/" + this.villager.getBackground() + " ');" +
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

    private void setType() {

        try {
            boolean buildable = this.villager.isBuilder();
            boolean trainable = this.villager.isTrainer();

            if (buildable && trainable) {
                this.type.getChildren().setAll(new TypeAllroundComponent());

            } else if (buildable) {
                this.type.getChildren().setAll(new TypeBuilderComponent());

            } else if (trainable) {
                this.type.getChildren().setAll(new TypeTrainerComponent());

            }

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }
}
