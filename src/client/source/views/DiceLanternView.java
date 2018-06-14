package client.source.views;

import client.source.components.villager.DiceController;
import client.source.components.villager.DiceLanternController;
import client.source.interfaces.ControllerInterface;
import client.source.interfaces.ViewInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DiceLanternView extends AnchorPane implements ViewInterface {

    @FXML private AnchorPane dice;
    @FXML private AnchorPane lanterns;

    private AnchorPane diceView;
    private AnchorPane lanternView;


    @Override
    public void load(ControllerInterface controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/resources/views/components/villager/dice_lantern.fxml"));

        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDiceView(AnchorPane diceView) {
        this.diceView = diceView;
    }

    public void setLanternView(AnchorPane lanternView) {
        this.lanternView = lanternView;
    }

    @Override
    public void show() {

        AnchorPane.setTopAnchor(this.diceView, 0d);
        AnchorPane.setLeftAnchor(this.diceView, 0d);
        AnchorPane.setBottomAnchor(this.diceView, 0d);
        AnchorPane.setRightAnchor(this.diceView, 0d);

        this.dice.getChildren().setAll(diceView);

        AnchorPane.setTopAnchor(this.lanternView, 0d);
        AnchorPane.setLeftAnchor(this.lanternView, 0d);
        AnchorPane.setBottomAnchor(this.lanternView, 0d);
        AnchorPane.setRightAnchor(this.lanternView, 0d);

        this.lanterns.getChildren().setAll(this.lanternView);

        this.lanterns = this.lanternView;
    }
}
