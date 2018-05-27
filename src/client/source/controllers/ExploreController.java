package client.source.controllers;

import client.source.Client;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import server.sources.models.stories.Story;

public class ExploreController implements ControllerInterface {

    private Client client;

    private Story exploreStory;

    @FXML Parent root;

    @FXML private TextArea story;

    @FXML private RadioButton option1;

    @FXML private RadioButton option2;

    @Override
    public Parent show() {
        story.setText(this.exploreStory.getStory());
        option1.setText(this.exploreStory.getChoices().get(0).getDescription());
        option2.setText(this.exploreStory.getChoices().get(1).getDescription());

        return this.root;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML public void clickChooce() {
        System.out.println("you clicked chooce");
    }

    public void setExploreStory(Story exploreStory) {
        this.exploreStory = exploreStory;
    }
}
