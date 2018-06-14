package client.source.components.villager;

import client.source.interfaces.ControllerInterface;
import client.source.views.DiceLanternView;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class DiceLanternController implements ControllerInterface {

    private DiceLanternView view;
    private DiceController diceController;
    private LanternController lanternController;

    public DiceLanternController(DiceLanternView view, DiceController diceController, LanternController lanternController) {
        this.diceController = diceController;
        this.lanternController = lanternController;

        view.load(this);
        this.view = view;
    }

    @Override
    public Parent update() {
        view.setDiceView((AnchorPane) diceController.update());
        view.setLanternView((AnchorPane) lanternController.update());

        this.view.show();

        return this.view;
    }
}
