package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.sources.actions.RunAction;
import server.sources.models.stories.Choice;
import server.sources.models.stories.Option;
import server.sources.models.stories.Story;

import java.rmi.RemoteException;

public class ExploreController implements ControllerInterface {

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
            for (Option option: this.exploreStory.getChoices().get(i).getOptions()) {
                Label lbl = new Label();
                lbl.setText("Explore " + option.getCost() + "   ");
                hBoxLanterns.getChildren().add(lbl);
            }
            vBox.getChildren().addAll(rbtn, hBoxLanterns);
            hbox.getChildren().addAll(vBox);
        }
        return this.root;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML public void clickRun() {
        try {
            client.getGameClient().getPlayer().doAction(new RunAction(client.getVillagerSelection().getSelectedVillagers()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML public void clickConfirm() {
        System.out.println("you clicked confirm");

        RadioButton selected = (RadioButton) radioGroup.getSelectedToggle();
        if(selected.getId().equals("0")){
            System.out.println(this.exploreStory.getChoices().get(0).getOptions());
            this.choice = this.exploreStory.getChoices().get(0);
        }else{
            System.out.println(this.exploreStory.getChoices().get(1).getOptions());
            this.choice = this.exploreStory.getChoices().get(1);
        }

        System.out.println( radioGroup.getSelectedToggle());
        client.showParty(this.exploreStory, this.choice);

    }

    public void setExploreStory(Story exploreStory) {
        this.exploreStory = exploreStory;
    }

    public void enableTurnButton() {
        this.runButton.setDisable(false);
    }

    public void disableTurnButton() {
        this.runButton.setDisable(true);
    }

    public void enableConfirmButton() {
        this.confirmButton.setDisable(false);
    }

    public void disableConfirmButton() {
        this.confirmButton.setDisable(true);
    }
}
