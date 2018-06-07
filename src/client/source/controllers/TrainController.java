package client.source.controllers;

import client.source.Client;
import client.source.components.villager_to_train.TypeAllroundComponent;
import client.source.components.villager_to_train.TypeBuilderComponent;
import client.source.components.villager_to_train.TypeTrainerComponent;
import client.source.components.villager_to_train.VillagerComponent;
import client.source.factories.VillagerSelectionFactory;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import server.sources.models.villagers.*;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by robin on 7-6-2018.
 */
public class TrainController implements ControllerInterface {

    public static Client client;
    @FXML
    private Parent root;
    @FXML private Button refreshButton;

    /**
     * Here are all villagerscontainers declared to stock villagers cards in villager market.
     * @author: Robin Silvério
     */
    @FXML private FlowPane villagersContainer;
    @FXML private FlowPane trainerVillagersContainer;
    @FXML private FlowPane builderVillagersContainer;
    @FXML private FlowPane allroundVillagersContainer;

    /**
     * And here are arraylists of villagers, where all villagers are stored inside.
     * @author Robin Silvério
     */

    private ArrayList<Villager> villagers = new ArrayList<Villager>();
    private VillagerFactory factory;


    private ArrayList<TrainerVillager> trainers = new ArrayList<TrainerVillager>();
    private ArrayList<BuilderVillager> builders = new ArrayList<BuilderVillager>();
    private ArrayList<AllroundVillager> allrounders = new ArrayList<AllroundVillager>();


    private ArrayList<VillagerComponent> villagerComponents;
    private ArrayList<TypeTrainerComponent> trainerComponents;
    private ArrayList<TypeBuilderComponent> builderComponents;
    private ArrayList<TypeAllroundComponent> allrounderComponents;

    ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

    @Override
    public Parent show() {

        try {
            this.retrieveVillagers();

            this.updateVillagersView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return this.root;
    }


    public void retrieveVillagers() throws RemoteException {
        for(int i = 0; i < 4; i++){
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            this.villagers.add(new Villager((ArrayList<Lantern>) lanterns.clone(), false, false));
        }
    }

    private void updateVillagersView() {
        this.villagerComponents = new ArrayList<VillagerComponent>();
        this.villagersContainer.getChildren().clear();


        for (int i = 0; i < this.villagers.size(); i++) {

            VillagerComponent villagerComponent = new VillagerComponent(this.villagers.get(i));
            this.villagerComponents.add(villagerComponent);
            this.villagersContainer.getChildren().add(villagerComponent);
        }
    }

//    /**
//     * This is for setting an ArrayList of villagers, retrieving it from a villagerfactory.
//     * @return
//     * @author: Robin Silvério
//     */
//    private ArrayList<Villager> setVillagersToArrayList(){
//
//        ArrayList<Villager> villagers = new ArrayList<Villager>();
//
//        villagers = factory.getVillagerList();
//
//        return villagers;
//    }

//    private void retrieveTrainerVillagers() throws RemoteException {
//
//    }
//
//    private void retrieveBuilderVillagers() throws RemoteException {
//
//    }
//
//    private void retrieveAllroundVillagers() throws RemoteException {
//
//    }
//
//    private void updateVillagersView() {
//    }
//    private void updateTrainerVillagersView() {
//    }
//    private void updateBuilderVillagersView() {
//    }
//    private void updateAllroundVillagersView() {
//    }

    public static void setClient(Client c) {
        client = c;
    }

}
