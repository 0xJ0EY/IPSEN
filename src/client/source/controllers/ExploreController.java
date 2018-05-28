package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import server.sources.models.stories.Story;

public class ExploreController implements ControllerInterface {

    private Client client;

    private Story exploreStory;

    @FXML Parent root;

    @FXML private TextArea story;

    @FXML private HBox hbox;

    @FXML private Button runButton;

    @Override
    public Parent show() {
        story.setText(this.exploreStory.getStory());

        for(int i=0; i<this.exploreStory.getChoices().size();i++){
            Button btn = new Button();
            btn.setId(""+i);
            btn.setText(this.exploreStory.getChoices().get(i).getDescription());
            hbox.getChildren().addAll(btn);
        }
        return this.root;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML public void clickRun() {
        System.out.println("you clicked run");
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
}
