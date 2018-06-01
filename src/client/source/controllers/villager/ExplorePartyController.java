package client.source.controllers.villager;

import client.source.Client;
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
    private ArrayList<VillagerComponent> villagerComponents;
    private ArrayList<Dice> dices;

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

        this.villagerComponents = new ArrayList<VillagerComponent>();
        this.villagerContainer.getChildren().clear();

        System.out.println(this.party);

        for (Villager villager : this.party) {

            VillagerComponent villagerComponent = new VillagerComponent(villager);
            this.villagerComponents.add(villagerComponent);
            this.villagerContainer.getChildren().add(villagerComponent);
        }
    }


    public void onClickRollDice(){
        Dice dice = new Dice();
        dice.roll();
        System.out.println(dice.returnValue());
    }

}
