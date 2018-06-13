package client.source.controllers;

import client.source.Client;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.sources.actions.EndTurnAction;
import server.sources.actions.RunAction;
import server.sources.actions.RewardAction;

import server.sources.interfaces.ActionInterface;
import server.sources.models.stories.Choice;
import server.sources.models.stories.Option;

import server.sources.interfaces.PlayerInterface;

import server.sources.models.stories.Story;
import server.sources.notifications.EndOfTurnNotification;

import java.rmi.RemoteException;

/**
 * A class that acts as an intermediary between an exploreview and models.
 * Created by Richard Kerkvliet
 */
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

    /**
     * For displaying an exploreview with loaded textarea full of stories and choices.
     * @return a loaded explore.FXML
     * @author Richard Kerkvliet
     */
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

    /**
     * This is for setting a client to a view.
     * @param client, a player that uses the application to play game online.
     * @author Richard Kerkvliet
     */
    public void setClient(Client client) {
        this.client = client;

        this.client.turnObserver.attach(this);
    }

    /**
     * This is for performing a click eventhandling when player wants to forfait the explore action.
     * @author Richard Kerkvliet
     */
    @FXML public void clickRun() {
        try {
            RunAction run = new RunAction();
            run.setSelectedVillagers(exploreStory.getVillagers());
            client.getGameClient().getPlayer().doAction(run);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is for performing a click eventhandling when player confirms the choice that he's made.
     * @author Richard Kerkvliet
     */
    @FXML public void clickConfirm() {
        RadioButton selected = (RadioButton) radioGroup.getSelectedToggle();

        if(selected.getId().equals("0")){
            this.choice = this.exploreStory.getChoices().get(0);
        }else{
            this.choice = this.exploreStory.getChoices().get(1);
        }

        client.showParty(this.exploreStory, this.choice);
    }

    /**
     * This is for observing any updates after ending turns and rounds or performing actions made by a player.
     * @author Richard Kerkvliet
     */
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

    /**
     * For setting a random explore story to be set on textarea.
     * @param exploreStory a random story generated.
     * @author Richard Kerkvliet
     */
    public void setExploreStory(Story exploreStory) {
        this.exploreStory = exploreStory;
    }
}
