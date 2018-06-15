package client.source.components.building;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HarvestablePerkComponent extends PerkComponent {

    public HarvestablePerkComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/harvestable_perk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
