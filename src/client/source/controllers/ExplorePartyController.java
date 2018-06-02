package client.source.controllers;

import client.source.Client;
import client.source.components.party.PartyVillagerComponent;
import client.source.components.villager.VillagerComponent;
import client.source.controllers.ControllerInterface;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import server.sources.actions.ExploreStoryAction;
import server.sources.actions.RunAction;
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
    private ArrayList<Villager> party;
    private ArrayList<PartyVillagerComponent> partyVillagerComponents;

    @Override
    public Parent show() throws RemoteException {

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

        this.choiceLabel.setText(this.choice.getDescription());

        this.partyVillagerComponents = new ArrayList<PartyVillagerComponent>();
        this.villagerContainer.getChildren().clear();

        for (Villager villager : this.party) {

            PartyVillagerComponent partyVillagerComponent = new PartyVillagerComponent(villager);
            this.partyVillagerComponents.add(partyVillagerComponent);
            this.villagerContainer.getChildren().add(partyVillagerComponent);
        }

        for (Option option : this.choice.getOptions()){
            Button btn = new Button("Explore " + option.getCost());
            btn.setPrefHeight(60.0);
            btn.setMinWidth(120.0);
            btn.setDisable(true);
            this.optionButtons.getChildren().add(btn);
        }

    }

    public void onClickRun(){
        try {
            client.getGameClient().getPlayer().doAction(new RunAction());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
