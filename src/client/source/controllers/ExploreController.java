package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import server.sources.actions.RunAction;
import server.sources.interfaces.PlayerInterface;
import server.sources.models.stories.Story;

import java.rmi.RemoteException;

public class ExploreController implements ControllerInterface, Observable {

    private Client client;

    private Story exploreStory;

    @FXML private Parent root;
    @FXML private TextArea story;
    @FXML private HBox hbox;
    @FXML private Button runButton;
    @FXML private Button confirmButton;
    @FXML private ToggleGroup radioGroup = new ToggleGroup();

    @Override
    public Parent show() {
        story.setText(this.exploreStory.getStory());
        hbox.getChildren().clear();

        for(int i=0; i<this.exploreStory.getChoices().size();i++){
            RadioButton rbtn = new RadioButton();
            rbtn.setId(""+i);
            rbtn.setText(this.exploreStory.getChoices().get(i).getDescription());
            rbtn.setToggleGroup(radioGroup);
            hbox.getChildren().addAll(rbtn);
        }
        return this.root;
    }

    public void setClient(Client client) {
        this.client = client;

        this.client.turnObserver.attach(this);
    }

    @FXML public void clickRun() {
        System.out.println("you clicked run");
        try {
            client.getGameClient().getPlayer().doAction(new RunAction());
            System.out.println("Test");

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("run finished");
    }

    @FXML public void clickConfirm() {
        System.out.println("you clicked confirm");

        client.showParty(this.exploreStory);

    }

    @Override
    public void updateObserver() {
        PlayerInterface target = this.client.turnObserver.getState();

        try {
            boolean turn = this.client.getGameClient().equals(target.getGameClient());
            this.runButton.setDisable(!turn);
            this.confirmButton.setDisable(!turn);


        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void setExploreStory(Story exploreStory) {
        this.exploreStory = exploreStory;
    }
}
