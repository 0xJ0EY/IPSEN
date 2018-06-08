package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.sources.actions.RunAction;
import server.sources.actions.RewardAction;

import server.sources.interfaces.ActionInterface;
import server.sources.models.stories.Choice;
import server.sources.models.stories.Option;

import server.sources.interfaces.PlayerInterface;

import server.sources.models.stories.Story;

import java.rmi.RemoteException;

public class ExploreController implements ControllerInterface, Observable {

    private Client client;
    private Choice choice;

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
            VBox vBox  = new VBox();
            vBox.setAlignment(Pos.CENTER);
            HBox hBoxLanterns = new HBox();
            hBoxLanterns.setAlignment(Pos.CENTER);
            RadioButton rbtn = new RadioButton();
            rbtn.setId(""+i);
            rbtn.setText(this.exploreStory.getChoices().get(i).getDescription());
            rbtn.setToggleGroup(radioGroup);
            rbtn.setOnMouseClicked( e-> {
                this.confirmButton.setDisable(false);
            });
            for (Option option: this.exploreStory.getChoices().get(i).getOptions()) {
                Label lbl = new Label();
                lbl.setText("Explore " + option.getCost() + "   ");
                hBoxLanterns.getChildren().add(lbl);
            }
            vBox.getChildren().addAll(rbtn, hBoxLanterns);
            hbox.getChildren().addAll(vBox);
        }
        this.updateObserver();
        return this.root;
    }

    public void setClient(Client client) {
        this.client = client;

        this.client.turnObserver.attach(this);
    }

    @FXML public void clickRun() {
        try {
            RunAction run = new RunAction();
            run.setSelectedVillagers(exploreStory.getVillagers());
            client.getGameClient().getPlayer().doAction(run);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void clickConfirm() {
        RadioButton selected = (RadioButton) radioGroup.getSelectedToggle();

        if(selected.getId().equals("0")){
            this.choice = this.exploreStory.getChoices().get(0);
        }else{
            this.choice = this.exploreStory.getChoices().get(1);
        }

        client.showParty(this.exploreStory, this.choice);
    }

    @Override
    public void updateObserver() {
        PlayerInterface target = this.client.turnObserver.getState();

        try {
            boolean turn;

            try {
                turn = this.client.getGameClient().equals(target.getGameClient());
            } catch (NullPointerException e) {
                turn = false;
            }

            this.runButton.setDisable(!turn);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void setExploreStory(Story exploreStory) {
        this.exploreStory = exploreStory;
    }
}
