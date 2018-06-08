package server.sources.models;

import server.sources.controllers.GameController;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.MarketInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;
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

    private ArrayList<Villager> villagers = new ArrayList<>();

    private House[] availableHouses = new House[4];
    private Outpost[] availableOutposts = new Outpost[4];
    private Villager[] availableVillagers = new Villager[5];

    private GameController gameController;

    public VillagerInterface[] listAvailableVillagers() {
        replenishVillagers();

        VillagerInterface[] villagers = new VillagerInterface[this.availableVillagers.length];

        for (int i = 0; i < this.availableVillagers.length; i++) {
            Villager villager = this.availableVillagers[i];

            villagers[i] = (VillagerInterface) villager;
        }

        return villagers;
    }

    public Market(GameController gameController) throws RemoteException {
        this.gameController = gameController;
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
            for(int i=0; i<availableVillagers.length; i++) {
                availableVillagers[i] = randomVillager();
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

    private Villager randomVillager(){
        int key = (int) (Math.random() * this.villagers.size());
        return this.villagers.get(key);
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
        for (int i = 0; i < availableVillagers.length; i++) {
            availableVillagers[i] = randomVillager();
        }
    }

    @Override
    public void buyRemoteVillager(GameClientInterface gameClient, VillagerInterface villagerInterface) throws RemoteException {

        Player localPlayer = null;

        // Get local player
        for (Player player : this.gameController.players) {
            if (player.getGameClient().equals(gameClient)) {
                localPlayer = player;
            }
        }

        for (Villager availableVillager : this.availableVillagers) {
            if (availableVillager.equals(villagerInterface)) {
                localPlayer.getPlayerBoard().addVillager(availableVillager);
            }
        }
    }
}
