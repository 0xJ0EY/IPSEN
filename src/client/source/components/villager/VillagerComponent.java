package client.source.components.villager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import server.sources.models.villagers.Villager;

import java.io.IOException;
import java.util.Random;

public class VillagerComponent extends AnchorPane {

    private Villager villager;

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

    // TODO: Remove this
    private String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }

}
