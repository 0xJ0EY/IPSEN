package client.source.controllers;

import client.source.Client;
import client.source.components.building.HouseComponent;
import client.source.components.building.KeyhouseComponent;
import client.source.components.building.OutpostComponent;
import client.source.components.building.StarhouseComponent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import server.sources.models.buildings.*;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 1-6-2018.
 */
public class BuildController implements ControllerInterface {

    public static Client client;
    @FXML private Parent root;
    @FXML private Button refreshButton;

    /**
     * Here are all buildingcontainers declared to store building cards in building market.
     */
    @FXML private FlowPane housesContainer;
    @FXML private FlowPane outpostsContainer;
    @FXML private FlowPane keyHousesContainer;
    @FXML private FlowPane starHousesContainer;

    /**
     * And here are arraylists of buildings, where all buildings are stored inside.
     */
    private ArrayList<House> houses = new ArrayList<House>();
    private ArrayList<Outpost> outposts = new ArrayList<Outpost>();
    private ArrayList<StarHouse> starHouses = new ArrayList<StarHouse>();
    private ArrayList<KeyHouse> keyHouses = new ArrayList<KeyHouse>();

    private ArrayList<HouseComponent> houseComponents;
    private ArrayList<OutpostComponent> outpostComponents;
    private ArrayList<StarhouseComponent> starhouseComponents;
    private ArrayList<KeyhouseComponent> keyhouseComponents;

    private BuildingFactory factory; // This is for loading all buildings that has been loaded from the start of the game.

    @Override
    public Parent show() {

        try {
            this.retrieveHouses();
            this.retrieveOutposts();
            this.retrieveStarhouses();
            this.retrieveKeyHouses();

            this.updateHousesView();
            this.updateOutpostsView();
            this.updateStarhousesView();
            this.updateKeyHousesView();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return this.root;
    }

    public void setClient(Client c) {
        client = c;
    }

    public Button getRefreshBtn(){
        return this.refreshButton;
    }

    /**
     * This is for getting all houses from a factory.
     * @throws RemoteException
     */
    public void retrieveHouses() throws RemoteException {
        this.houses = setHousesToArrayList();
    }

    /**
     * This is for updating all houses to its container.
     * @throws RemoteException
     */
    public void updateHousesView(){

        this.houseComponents = new ArrayList<HouseComponent>();
        this.housesContainer.getChildren().clear();


        for (int i = 0; i < 5; i++) {

            HouseComponent houseComponent = new HouseComponent(this.houses.get(i));
            this.houseComponents.add(houseComponent);
            this.housesContainer.getChildren().add(houseComponent);
        }
    }

    /**
     * This is for setting an ArrayList of houses, retrieving it from a buildingfactory.
     * @return
     */
    private ArrayList<House> setHousesToArrayList(){

        ArrayList<House> houses = new ArrayList<House>();

        try {
            factory = new BuildingFactory();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        houses = factory.loadHousesFromXML();

        return houses;
    }

    /**
     * This is for getting all outposts from a factory.
     * @throws RemoteException
     */
    public void retrieveOutposts() throws RemoteException {
        this.outposts = setOutpostsToArrayList();
    }

    /**
     * This is for updating all outposts to its container.
     * @throws RemoteException
     */
    public void updateOutpostsView(){

        this.outpostComponents = new ArrayList<OutpostComponent>();
        this.outpostsContainer.getChildren().clear();


        for (int i = 0; i < 4; i++) {

            OutpostComponent opc = new OutpostComponent(this.outposts.get(i));
            this.outpostComponents.add(opc);
            this.outpostsContainer.getChildren().add(opc);
        }
    }

    /**
     * This is for setting an ArrayList of outposts, retrieving it from a buildingfactory.
     * @return
     */
    private ArrayList<Outpost> setOutpostsToArrayList(){

        ArrayList<Outpost> outposts = new ArrayList<Outpost>();

        try {
            factory = new BuildingFactory();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        outposts = factory.loadOutpostsFromXML();

        return outposts;
    }

    /**
     * This is for getting all starhouses from a factory.
     * @throws RemoteException
     */
    public void retrieveStarhouses() throws RemoteException {
        this.starHouses = setStarHouseToArrayList();
    }

    /**
     * This is for updating all starhouses to its container.
     * @throws RemoteException
     */
    public void updateStarhousesView(){

        this.starhouseComponents = new ArrayList<StarhouseComponent>();
        this.starHousesContainer.getChildren().clear();


        for (int i = 0; i < 4; i++) {

            StarhouseComponent shc = new StarhouseComponent(this.starHouses.get(i));
            this.starhouseComponents.add(shc);
            this.starHousesContainer.getChildren().add(shc);
        }
    }

    /**
     * This is for setting an ArrayList of starhouses, retrieving it from a buildingfactory.
     * @return
     */
    private ArrayList<StarHouse> setStarHouseToArrayList(){

        ArrayList<StarHouse> starHouses = new ArrayList<StarHouse>();

        try {
            factory = new BuildingFactory();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        starHouses = factory.loadStarHousesFromXML();

        return starHouses;
    }

    /**
     * This is for getting all keyhouses from a factory.
     * @throws RemoteException
     */
    public void retrieveKeyHouses() throws RemoteException {
        this.keyHouses = setKeyHouseToArrayList();
    }

    /**
     * This is for updating all keyhouses to its container.
     * @throws RemoteException
     */
    public void updateKeyHousesView(){

        this.keyhouseComponents = new ArrayList<KeyhouseComponent>();
        this.keyHousesContainer.getChildren().clear();

        for (int i = 0; i < 4; i++) {

            KeyhouseComponent khc = new KeyhouseComponent(this.keyHouses.get(i));
            this.keyhouseComponents.add(khc);
            this.keyHousesContainer.getChildren().add(khc);
        }
    }

    /**
     * This is for setting an ArrayList of keyhouses, retrieving it from a buildingfactory.
     * @return
     */
    private ArrayList<KeyHouse> setKeyHouseToArrayList(){

        ArrayList<KeyHouse> keyHouses = new ArrayList<KeyHouse>();

        try {
            factory = new BuildingFactory();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        keyHouses = factory.loadKeyHousesFromXML();

        return keyHouses;
    }

}
