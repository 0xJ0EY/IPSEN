package client.source.controllers;

import client.source.Client;
import client.source.components.party.OptionButtonComponent;
import client.source.components.party.PartyVillagerComponent;
import client.source.components.villager.VillagerComponent;
import client.source.controllers.ControllerInterface;
import client.source.observers.Observable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import server.sources.actions.ExploreStoryAction;
import server.sources.actions.RunAction;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.stories.Choice;
import server.sources.models.stories.Option;
import server.sources.models.stories.Story;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ExplorePartyController implements ControllerInterface {

    @FXML private Parent root;
    @FXML private Label choiceLabel;
    @FXML private FlowPane villagerContainer;
    @FXML private Label currentLanterns;
    @FXML private HBox optionButtons;

    private Client client;
    private Story story;
    private Choice choice;
    private ArrayList<VillagerInterface> party;
    private ArrayList<PartyVillagerComponent> partyVillagerComponents;
    private ArrayList<OptionButtonComponent> optionButtonComponents;
    private int totalLanternScore;

    @Override
    public Parent show() {

        // TODO: Maybe make this possible with observers (Should be easy)
        this.retrieveParty();

        this.updatePartyView();

        return this.root;
    }

    private void retrieveParty() {
        this.party = story.getVillagers();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    private void updatePartyView() {

        this.villagerContainer.setAlignment(Pos.CENTER);
        this.currentLanterns.setAlignment(Pos.CENTER);
        this.choiceLabel.setAlignment(Pos.CENTER);

        this.totalLanternScore = 0;
        this.currentLanterns.setText("");

        this.choiceLabel.setText(this.choice.getDescription());

        this.partyVillagerComponents = new ArrayList<PartyVillagerComponent>();
        this.optionButtonComponents = new ArrayList<OptionButtonComponent>();
        this.villagerContainer.getChildren().clear();
        this.optionButtons.getChildren().clear();

        for (VillagerInterface villager : this.party) {

            try {
                PartyVillagerComponent partyVillagerComponent = new PartyVillagerComponent(villager, this);
                this.partyVillagerComponents.add(partyVillagerComponent);
                this.villagerContainer.getChildren().add(partyVillagerComponent);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        for (Option option : this.choice.getOptions()){

            OptionButtonComponent optionButtonComponent = new OptionButtonComponent(option, client);
            this.optionButtonComponents.add(optionButtonComponent);
            this.optionButtons.getChildren().add(optionButtonComponent);
        }

    }

    public void updateLanternScore(int lanternScore){
        this.totalLanternScore += lanternScore;
        this.currentLanterns.setText("Total number of lanterns: " + totalLanternScore);

        for (OptionButtonComponent optionButtonComponent : optionButtonComponents) {
            optionButtonComponent.enableOption(this.totalLanternScore);
        }
    }

    public void onClickRun(){
        try {
            client.getGameClient().getPlayer().doAction(new RunAction(party));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
