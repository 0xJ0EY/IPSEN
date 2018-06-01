package client.source.controllers;

import client.source.Client;
import client.source.components.party.PartyVillagerComponent;
import client.source.components.villager.VillagerComponent;
import client.source.controllers.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import server.sources.actions.ExploreStoryAction;
import server.sources.models.Dice;
import server.sources.models.stories.Story;
import server.sources.models.villagers.Villager;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ExplorePartyController implements ControllerInterface {

    @FXML private Parent root;
    @FXML private FlowPane villagerContainer;

    private Client client;
    private Story story;
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

    private void updatePartyView() {

        this.partyVillagerComponents = new ArrayList<PartyVillagerComponent>();
        this.villagerContainer.getChildren().clear();

        System.out.println(this.party);

        for (Villager villager : this.party) {

            PartyVillagerComponent partyVillagerComponent = new PartyVillagerComponent(villager);
            this.partyVillagerComponents.add(partyVillagerComponent);
            this.villagerContainer.getChildren().add(partyVillagerComponent);
        }
    }


    public void onClickRollDice(){
        Dice dice = new Dice();
        dice.roll();
        System.out.println(dice.returnValue());
    }

}
