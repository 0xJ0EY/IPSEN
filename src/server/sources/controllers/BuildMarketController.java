package server.sources.controllers;

import server.sources.interfaces.MarketControllerInterface;
import server.sources.models.Player;
import server.sources.models.buildings.*;
import server.sources.models.villagers.VillagerFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by robin on 1-6-2018.
 */
public class BuildMarketController extends UnicastRemoteObject implements MarketControllerInterface {

    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<StarHouse> starHouses = new ArrayList<>();
    private ArrayList<KeyHouse> keyHouses = new ArrayList<>();
    private ArrayList<Outpost> outposts = new ArrayList<>();

    private House[] availableHouses = new House[4];
    private Outpost[] availableOutposts = new Outpost[4];

    public BuildMarketController() throws RemoteException{

    }


    public void load() {

        try {
            BuildingFactory bf = new BuildingFactory();

            houses = bf.loadHousesFromXML();
            starHouses = bf.loadStarHousesFromXML();
            keyHouses = bf.loadKeyHousesFromXML();
            outposts = bf.loadOutpostsFromXML();

            for(int i=0; i<availableHouses.length; i++) {
                availableHouses[i] =  randomHouse();
                availableOutposts[i] = randomOutpost();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    public void refreshHouses(Player player){
        for (int i=0; i<availableHouses.length; i++) {
            availableHouses[i] = randomHouse();
        }
        for (int i=0; i<availableOutposts.length; i++){
            availableOutposts[i] = randomOutpost();
        }
    }

    private House randomHouse(){
        int key = (int) (Math.random() * this.houses.size());
        return this.houses.get(key);
    }

    private Outpost randomOutpost(){
        int key = (int) (Math.random() * this.outposts.size());
        return this.outposts.get(key);
    }

    public void replenishHouses(){
        //TODO could be at the end of buy action, in this case you can directly give the index of the bought house
        for (Object house:availableHouses) {
            if(house == null){
                house = randomHouse();
            }
        }
    }

    public void replenishOutposts(){
        //TODO could be at the end of buy action, in this case you can directly give the index of the bought outpost
        for (Object outpost:availableOutposts){
            if(outpost == null){
                outpost = randomOutpost();
            }
        }
    }

    public void buyHouse(Player player, House house){

    }

    public void buyOutposts(Player player, House house){

    }

    public void buyStarHouse(Player player, House house){

    }

    public void buyKeyHouse(Player player, House house){

    }
}