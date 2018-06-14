package client.source.components.villager;

import client.source.interfaces.ControllerInterface;
import client.source.views.DiceView;
import javafx.scene.Parent;
import server.sources.models.Dice;

public class DiceController implements ControllerInterface {

    private DiceView view;
    private Dice model;

    public DiceController(DiceView view, Dice model) {
        this.model = model;

        view.load(this);
        this.view = view;
    }

    @Override
    public Parent update() {

        model.roll();
        view.setEyes(model.getEyes());

        view.show();

        return this.view;
    }
}
