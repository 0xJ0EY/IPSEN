package client.source.components.villager;

import client.source.interfaces.ControllerInterface;
import client.source.views.LanternView;
import javafx.scene.Parent;
import server.sources.models.villagers.Lantern;

public class LanternController implements ControllerInterface {

    private LanternView view;
    private Lantern model;

    public LanternController(LanternView view, Lantern model) {
        this.model = model;

        view.load(this);
        this.view = view;
    }

    public Parent update() {

        // Update view
        this.view.setLanterns(model.getAmount());

        this.view.show();

        return this.view;
    }
}
