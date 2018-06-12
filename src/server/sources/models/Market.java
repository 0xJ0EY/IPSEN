package server.sources.models;

import server.sources.controllers.GameController;
import server.sources.interfaces.*;
import server.sources.models.buildings.*;
import server.sources.models.villagers.Villager;
import server.sources.models.villagers.VillagerFactory;
import server.sources.notifications.MarketUpdateNotification;

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

    private GameController gameController;

    public VillagerInterface[] listAvailableVillagers() {

        VillagerInterface[] villagers = new VillagerInterface[this.availableVillagers.length];

        for (int i = 0; i < this.availableVillagers.length; i++) {
            Villager villager = this.availableVillagers[i];
            villagers[i] = villager;
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

    public void replenishVillagers(){
        for (int i = 0; i < availableVillagers.length; i++) {
            availableVillagers[i] = randomVillager();
        }

        try {
            this.updateObserver();
        } catch (RemoteException e) {
            e.printStackTrace();
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

    @Override
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

        this.updateObserver();
    }

    @Override
    public ArrayList<MarketHouse> listAvailableHouses() throws RemoteException {
        ArrayList<MarketHouse> houses = new ArrayList<MarketHouse>();

        for (MarketHouse availableHouse : this.availableHouses) {
            if (availableHouse == null) continue;
            houses.add(availableHouse);
        }

        return houses;
    }

    @Override
    public ArrayList<MarketKeyHouse> listAvailableKeyHouses() throws RemoteException {
        return this.keyHouses;
    }

    @Override
    public ArrayList<MarketStarHouse> listAvailableStarHouses() throws RemoteException {
        return this.starHouses;
    }

    @Override
    public ArrayList<MarketOutpost> listAvailableOutposts() throws RemoteException {
        return new ArrayList<MarketOutpost>(Arrays.asList(this.availableOutposts));
    }

    @Override
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

        this.updateObserver();
    }

    @Override
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

        this.updateObserver();
    }

    @Override
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

        this.updateObserver();
    }

    @Override
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

        this.updateObserver();
    }


    @Override
    public void refreshHousesAndOutposts() throws RemoteException {
        // TODO: 12/06/2018 PAY coin 
        for (int i = 0; i < this.availableHouses.length; i++) {
            this.availableHouses[i] = this.randomHouse();
            this.availableOutposts[i] = this.randomOutpost();
        }
        this.updateObserver();
    }

    @Override
    public void updateObserver() throws RemoteException {
        this.gameController.server.notifyClients(new MarketUpdateNotification(this));
    }
}
