package client.source.controllers;

import client.source.Client;
import client.source.components.villager_to_train.VillagerComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import server.sources.models.villagers.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
    private ArrayList<VillagerComponent> trainerComponents;
    private ArrayList<VillagerComponent> builderComponents;
    private ArrayList<VillagerComponent> allrounderComponents;

    ArrayList<Lantern> lanterns;

    @Override
    public Parent show() {

        try {
            this.retrieveVillagers();
            this.retrieveTrainerVillagers();
            this.retrieveBuilderVillagers();
            this.retrieveAllroundVillagers();

            this.updateVillagersView();
            this.updateTrainersView();
            this.updateBuilderVillagersView();
            this.updateAllroundVillagersView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return this.root;
    }

    /**
     * For getting all villagers
     * @throws RemoteException
     * @author: Robin Silvério
     */
    public void retrieveVillagers() throws RemoteException {
        this.villagers = setVillagersToArrayList();
    }

    /**
     * For updating all common villagers to its containers.
     * @author: Robin Silvério
     */
    private void updateVillagersView() {
        this.villagerComponents = new ArrayList<VillagerComponent>();
        this.villagersContainer.getChildren().clear();


        for (int i = 0; i < this.villagers.size(); i++) {

            VillagerComponent villagerComponent = new VillagerComponent(this.villagers.get(i));
            this.villagerComponents.add(villagerComponent);
            this.villagersContainer.getChildren().add(villagerComponent);
        }
    }

    /**
     * This is for setting an ArrayList of villagers, retrieving it from a villagerfactory.
     * @return
     * @author: Robin Silvério
     */
    private ArrayList<Villager> setVillagersToArrayList(){

        ArrayList<Villager> villagers = new ArrayList<Villager>();
        lanterns = new ArrayList<Lantern>();
        for(int i = 0; i < 5; i++){
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            villagers.add(new Villager((ArrayList<Lantern>) lanterns.clone(), false, false));
        }

        return villagers;
    }

    /**
     * For getting all trainerVillagers
     * @throws RemoteException
     * @author Robin Silvério
     */
    private void retrieveTrainerVillagers() throws RemoteException {
        this.trainers = setTrainersToArrayList();
    }


    /**
     * For updating all trainervillagers to its containers.
     * @author: Robin Silvério
     */
    private void updateTrainersView() {
        this.trainerComponents = new ArrayList<VillagerComponent>();
        this.trainerVillagersContainer.getChildren().clear();


        for (int i = 0; i < this.trainers.size(); i++) {

            VillagerComponent trainerComponent = new VillagerComponent(this.trainers.get(i));
            this.trainerComponents.add(trainerComponent);
            this.trainerVillagersContainer.getChildren().add(trainerComponent);
        }
    }

    /**
     * This is for setting an ArrayList of trainerVillagers
     * @return villagers arrayLists met trainer Villagers
     * @author: Robin Silvério
     */
    private ArrayList<TrainerVillager> setTrainersToArrayList(){

        ArrayList<TrainerVillager> trainers = new ArrayList<TrainerVillager>();
        lanterns = new ArrayList<Lantern>();
        for(int i = 0; i < 5; i++){
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            trainers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), false, false));
        }

        return trainers;
    }

    /**
     * For getting all builderVillagers
     * @throws RemoteException
     * @author Robin Silvério
     */
    private void retrieveBuilderVillagers() throws RemoteException {
        this.builders = setBuildersToArrayList();
    }

    private void updateBuilderVillagersView() {
        this.builderComponents = new ArrayList<VillagerComponent>();
        this.builderVillagersContainer.getChildren().clear();


        for (int i = 0; i < this.builders.size(); i++) {

            VillagerComponent builderComponent = new VillagerComponent(this.builders.get(i));
            this.builderComponents.add(builderComponent);
            this.builderVillagersContainer.getChildren().add(builderComponent);
        }
    }

    private ArrayList<BuilderVillager> setBuildersToArrayList() {
        ArrayList<BuilderVillager> builders = new ArrayList<BuilderVillager>();

        for (int i = 0; i < 5; i++) {
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            builders.add(new BuilderVillager((ArrayList<Lantern>) lanterns.clone(), false, false));
        }

        return builders;
    }


    /**
     * For retrieving all allroundvillagers
     * @throws RemoteException
     * @author Robin Silvério
     */
    private void retrieveAllroundVillagers() throws RemoteException {
        this.allrounders = setAllroundVillagersToArrayList();
    }

    private ArrayList<AllroundVillager> setAllroundVillagersToArrayList() {
        ArrayList<AllroundVillager> allroundVillagers = new ArrayList<AllroundVillager>();

        for (int i = 0; i < 5; i++) {
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            lanterns.add(new Lantern((int)(Math.random() * 10) + 1, (int)(Math.random() * 5) + 1));
            allroundVillagers.add(new AllroundVillager((ArrayList<Lantern>) lanterns.clone(), false, false));
        }

        return allroundVillagers;
    }

    private void updateAllroundVillagersView() {
        this.allrounderComponents = new ArrayList<VillagerComponent>();
        this.allroundVillagersContainer.getChildren().clear();


        for (int i = 0; i < this.allrounders.size(); i++) {

            VillagerComponent allroundComponent = new VillagerComponent(this.allrounders.get(i));
            this.allrounderComponents.add(allroundComponent);
            this.allroundVillagersContainer.getChildren().add(allroundComponent);
        }
    }



    public static void setClient(Client c) {
        client = c;
    }

}
