package server.sources.models;

import server.sources.controllers.GoodOnSale;
import server.sources.interfaces.MarketInterface;

import server.sources.models.buildings.*;
import server.sources.models.villagers.Villager;
import server.sources.models.villagers.VillagerFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Market extends UnicastRemoteObject implements MarketInterface {

    private static final long serialVersionUID = 1337L;

    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<StarHouse> starHouses = new ArrayList<>();
    private ArrayList<KeyHouse> keyHouses = new ArrayList<>();
    private ArrayList<Outpost> outposts = new ArrayList<>();
    private ArrayList<GoodOnSale> goodsOnSale = new ArrayList<GoodOnSale>();

    private House[] availableHouses = new House[4];
    private Outpost[] availableOutposts = new Outpost[4];

    private ArrayList<Villager> villagers = new ArrayList<>();

    public Market() throws RemoteException {

    }

    public void load() {

        try {
            BuildingFactory bf = new BuildingFactory();
            VillagerFactory vf = new VillagerFactory();

            houses = bf.loadHousesFromXML();
            starHouses = bf.loadStarHousesFromXML();
            keyHouses = bf.loadKeyHousesFromXML();
            outposts = bf.loadOutpostsFromXML();
            villagers = vf.createFromXml();

            for(int i=0; i<availableHouses.length; i++) {
                availableHouses[i] =  randomHouse();
                availableOutposts[i] = randomOutpost();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    //TODO: invullen methods
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

    public void replenishVillagers(){

    }

    public void buyHouse(Player player, House house){

    }

    public void buyOutposts(Player player, House house){

    }

    public void buyStarHouse(Player player, House house){

    }

    public void buyKeyHouse(Player player, House house){

    }

    public void buyVillager(Player player, Villager villager){

    }

    public void sellGood(GoodOnSale goodOnSale){
        this.goodsOnSale.add(goodOnSale);
    }

}
