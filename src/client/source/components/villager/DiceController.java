package client.source.components.villager;

import client.source.interfaces.ControllerInterface;
import client.source.models.DisplayDice;
import client.source.views.DiceView;
import javafx.scene.Parent;
import server.sources.models.Dice;

public class DiceController implements ControllerInterface {

    private DiceView view;
    private DisplayDice model;

    public DiceController(DiceView view, DisplayDice model) {
        this.model = model;

        view.load(this);
        this.view = view;
    }

    @Override
    public Parent update() {

        view.setEyes(model.read());

        view.show();

        return this.view;
    }
}
