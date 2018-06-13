package client.source.components.building;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * A class the loads perkcomponents that are refreshed after that the round has been ended.
 * Created by Joey de Ruiter
 */
public class RefreshablePerkComponent extends PerkComponent {

    public RefreshablePerkComponent() {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/building/refreshable_perk.fxml"));

        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
