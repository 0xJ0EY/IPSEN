package client.source.components.building;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

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
