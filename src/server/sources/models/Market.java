package server.sources.models;

import server.sources.controllers.GoodOnSale;
import server.sources.interfaces.MarketInterface;

import server.sources.controllers.GameController;
import server.sources.interfaces.*;
import server.sources.models.buildings.*;
import server.sources.models.goods.Good;
import server.sources.models.villagers.Villager;
import server.sources.models.villagers.VillagerFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

public class Market extends UnicastRemoteObject implements MarketInterface {

    private static final long serialVersionUID = 1337L;

    private ArrayList<MarketHouse> houses = new ArrayList<>();
    private ArrayList<MarketStarHouse> starHouses = new ArrayList<>();
    private ArrayList<MarketKeyHouse> keyHouses = new ArrayList<>();
    private ArrayList<MarketOutpost> outposts = new ArrayList<>();

    private ArrayList<Villager> villagers = new ArrayList<>();

    private MarketHouse[] availableHouses = new MarketHouse[4];
    private MarketOutpost[] availableOutposts = new MarketOutpost[4];
    private Villager[] availableVillagers = new Villager[5];

    private ArrayList<GoodOnSaleInterface> goodsOnSale = new ArrayList<>();

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
            BuildingFactory buildingFactory = new BuildingFactory();
            VillagerFactory villagerFactory = new VillagerFactory();

            houses = buildingFactory.loadHousesFromXML();
            starHouses = buildingFactory.loadStarHousesFromXML();
            keyHouses = buildingFactory.loadKeyHousesFromXML();
            outposts = buildingFactory.loadOutpostsFromXML();
            villagers = villagerFactory.createFromXml();

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

    public void refreshHouses(Player player){
        for (int i = 0; i< availableHouses.length; i++) {
            availableHouses[i] = randomHouse();
        }
        for (int i = 0 ; i< availableOutposts.length; i++){
            availableOutposts[i] = randomOutpost();
        }
    }

    private MarketHouse randomHouse(){
        int key = (int) (Math.random() * this.houses.size());
        return this.houses.get(key);
    }

    private MarketOutpost randomOutpost(){
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

    private Player getLocalPlayer(GameClientInterface gameClient) throws RemoteException {

        for (Player player : this.gameController.players) {
            if (player.getGameClient().equals(gameClient)) {
                return player;
            }
        }

        return null;
    }

    public void buyRemoteVillager(GameClientInterface gameClient, VillagerInterface villagerInterface) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        for (int i = 0; i < this.availableVillagers.length; i++) {
            Villager availableVillager = this.availableVillagers[i];

            if (availableVillager.equals(villagerInterface)) {
                localPlayer.getPlayerBoard().addVillager(availableVillager);
                localPlayer.getPlayerBoard().updateObserver();
                this.availableVillagers[i] = null;
            }
        }
    }

    public ArrayList<MarketHouse> listAvailableHouses() throws RemoteException {
        ArrayList<MarketHouse> houses = new ArrayList<MarketHouse>();

        for (MarketHouse availableHouse : this.availableHouses) {
            if (availableHouse == null) continue;
            houses.add(availableHouse);
        }

        return houses;
    }

    public ArrayList<MarketKeyHouse> listAvailableKeyHouses() throws RemoteException {
        return this.keyHouses;
    }

    public ArrayList<MarketStarHouse> listAvailableStarHouses() throws RemoteException {
        return this.starHouses;
    }

    public ArrayList<MarketOutpost> listAvailableOutposts() throws RemoteException {
        return new ArrayList<MarketOutpost>(Arrays.asList(this.availableOutposts));
    }

    public void buyRemoteHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        if (localPlayer.getPlayerBoard().getCoins() < house.getCost()) return;

        for (int i = 0; i < this.availableHouses.length; i++) {
            MarketHouse marketHouse = this.availableHouses[i];

            if (marketHouse.equals(house)) {
                localPlayer.getPlayerBoard().addHouse((House) marketHouse);
                localPlayer.getPlayerBoard().payCoin(marketHouse.getCost());

                localPlayer.getPlayerBoard().updateObserver();

                this.houses.remove(marketHouse);
                this.availableHouses[i] = this.randomHouse();
            }
        }
    }

    public void buyRemoteOutpost(GameClientInterface gameClient, BuildingInterface outpost) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        if (localPlayer.getPlayerBoard().getCoins() < outpost.getCost()) return;

        for (int i = 0; i < this.availableOutposts.length; i++) {
            MarketOutpost marketOutpost = this.availableOutposts[i];

            if (marketOutpost.equals(outpost)) {
                localPlayer.getPlayerBoard().addOutpost((Outpost) marketOutpost);
                localPlayer.getPlayerBoard().payCoin(marketOutpost.getCost());

                localPlayer.getPlayerBoard().updateObserver();

                // Replace the outpost with a random outpost
                this.outposts.remove(marketOutpost);
                this.availableOutposts[i] = this.randomOutpost();
            }
        }
    }

    public void buyRemoteKeyHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        if (localPlayer.getPlayerBoard().getCoins() < house.getCost()) return;

        for (MarketKeyHouse keyHouse : this.keyHouses) {
            if (keyHouse.equals(house)) {
                localPlayer.getPlayerBoard().addHouse((KeyHouse) keyHouse);
                localPlayer.getPlayerBoard().payCoin(keyHouse.getCost());

                localPlayer.getPlayerBoard().updateObserver();
                this.keyHouses.remove(keyHouse);
            }
        }
    }

    public void buyRemoteStarHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        if (localPlayer.getPlayerBoard().getCoins() < house.getCost()) return;

        for (MarketStarHouse starHouse : this.starHouses) {
            if (starHouse.equals(house)) {
                localPlayer.getPlayerBoard().addHouse((StarHouse) starHouse);
                localPlayer.getPlayerBoard().payCoin(starHouse.getCost());

                localPlayer.getPlayerBoard().updateObserver();
                this.starHouses.remove(starHouse);
            }
        }
    }

    @Override
    public void sellGood(Good good, GameClientInterface client) throws RemoteException{
        this.goodsOnSale.add(new GoodOnSale(client, good));
        System.out.println(goodsOnSale);
    }

    @Override
    public ArrayList<GoodOnSaleInterface> getGoodList() throws RemoteException{
        return this.goodsOnSale;
    }

    @Override
    public void buyGood(int index) throws RemoteException{
        this.goodsOnSale.remove(index);
    }

}
