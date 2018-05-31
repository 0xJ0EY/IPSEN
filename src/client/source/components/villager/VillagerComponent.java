package client.source.components.villager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.models.villagers.Villager;

import java.io.IOException;

public class VillagerComponent extends AnchorPane {

    private Villager villager;

    private boolean selected;

    @FXML AnchorPane background;

    @FXML Text labelType;

    @FXML AnchorPane type;

    public VillagerComponent(Villager villager) {
        this.villager = villager;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/views/components/villager/villager.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.type.getChildren().setAll(villager.getType());
    }

    @FXML
    private void onClickSelect() {
        // TODO: Show indicator
        this.selected = !this.selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public Villager getVillager() {
        return villager;
    }
}
