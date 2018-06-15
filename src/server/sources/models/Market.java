package server.sources.models;

import javafx.application.Platform;
import server.sources.interfaces.MarketInterface;
import server.sources.controllers.GameController;
import server.sources.interfaces.*;
import server.sources.models.buildings.*;
import server.sources.models.goods.Good;
import server.sources.models.goods.GoodOnSale;
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

    private ArrayList<GoodOnSaleInterface> goodsOnSale = new ArrayList<>();

    private GameController gameController;

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

            for(int i = 0; i <availableHouses.length; i++) {
                availableHouses[i] =  randomHouse();
                availableOutposts[i] = randomOutpost();
            }
            for(int i = 0; i< availableVillagers.length; i++) {
                availableVillagers[i] = randomVillager();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private MarketHouse randomHouse() {
        if (this.houses.size() == 0) return null;
        int key = (int) (Math.floor(Math.random() * this.houses.size()));

        // Get the house from the available pool
        MarketHouse house = this.houses.get(key);

        // Remove the house from the available pool
        this.houses.remove(key);

        return house;
    }

    private MarketOutpost randomOutpost() {
        if (this.outposts.size() == 0) return null;
        int key = (int) (Math.floor(Math.random() * this.outposts.size()));

        // Get the house from the available pool
        MarketOutpost outpost = this.outposts.get(key);

        // Remove the house from the available pool
        this.outposts.remove(key);

        return outpost;
    }

    private Villager randomVillager() {
        if (this.villagers.size() == 0) return null;
        int key = (int) (Math.floor(Math.random() * this.villagers.size()));

        // Get the villager from the available pool
        Villager villager = this.villagers.get(key);

        // Remove the villager from the available pool
        this.villagers.remove(key);

        return villager;
    }

    private Player getLocalPlayer(GameClientInterface gameClient) throws RemoteException {

        for (Player player : this.gameController.players) {
            if (player.getGameClient().equals(gameClient)) {
                return player;
            }
        }

        return null;
    }

    /**
     * List all available villagers to be purchased.
     *
     * @author Richard Kerkvliet
     * @return VillagerInterface[]
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public VillagerInterface[] listAvailableVillagers() throws RemoteException {

        VillagerInterface[] villagers = new VillagerInterface[this.availableVillagers.length];

        for (int i = 0; i < this.availableVillagers.length; i++) {
            Villager villager = this.availableVillagers[i];
            villagers[i] = villager;
        }

        return villagers;
    }

    public ArrayList<MarketHouse> listAvailableHouses() throws RemoteException {
        ArrayList<MarketHouse> houses = new ArrayList<MarketHouse>();

        for (MarketHouse availableHouse : this.availableHouses) {
            if (availableHouse == null) continue;
            houses.add(availableHouse);
        }

        return houses;
    }

    /**
     * List all available key houses listed for purchased.
     *
     * @author Joey de Ruiter
     * @return ArrayList
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<MarketKeyHouse> listAvailableKeyHouses() throws RemoteException {
        return this.keyHouses;
    }

    /**
     * List all available star houses listed for purchase.
     *
     * @author Joey de Ruiter
     * @return ArrayList
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<MarketStarHouse> listAvailableStarHouses() throws RemoteException {
        return this.starHouses;
    }

    /**
     * List all available outposts to be purchased.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<MarketOutpost> listAvailableOutposts() throws RemoteException {
        return new ArrayList<MarketOutpost>(Arrays.asList(this.availableOutposts));
    }

    /**
     * Move a local villager object to the villagerInterface.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param villagerInterface
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public void buyRemoteVillager(GameClientInterface gameClient, VillagerInterface villagerInterface) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        for (int i = 0; i < this.availableVillagers.length; i++) {
            if (this.availableVillagers[i] == null) continue;

            Villager availableVillager = this.availableVillagers[i];

            if (availableVillager.equals(villagerInterface)) {
                localPlayer.getPlayerBoard().addVillager(availableVillager);
                this.availableVillagers[i] = null;
            }
        }

        this.updateObserver();
    }

    @Override
    public synchronized void buyRemoteHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        if (localPlayer.getPlayerBoard().getCoins() < house.getCost()) return;

        for (int i = 0; i < this.availableHouses.length; i++) {
            MarketHouse marketHouse = this.availableHouses[i];

            if (marketHouse.equals(house)) {
                localPlayer.getPlayerBoard().addHouse((House) marketHouse);
                localPlayer.getPlayerBoard().payCoin(marketHouse.getCost());

                this.houses.remove(marketHouse);
                this.availableHouses[i] = this.randomHouse();
            }
        }

        this.updateObserver();
    }

    /**
     * Move a local outpost to the playerboard of the given gameClient.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param outpost
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public synchronized void buyRemoteOutpost(GameClientInterface gameClient, BuildingInterface outpost) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        if (localPlayer.getPlayerBoard().getCoins() < outpost.getCost()) return;

        for (int i = 0; i < this.availableOutposts.length; i++) {
            MarketOutpost marketOutpost = this.availableOutposts[i];

            if (marketOutpost.equals(outpost)) {
                localPlayer.getPlayerBoard().addOutpost((Outpost) marketOutpost);
                localPlayer.getPlayerBoard().payCoin(marketOutpost.getCost());

                // Replace the outpost with a random outpost
                this.outposts.remove(marketOutpost);
                this.availableOutposts[i] = this.randomOutpost();
            }
        }

        this.updateObserver();
    }

    /**
     * Move a local key house to the playerboard of the given gameClient.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param house
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public synchronized void buyRemoteKeyHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        if (localPlayer.getPlayerBoard().getCoins() < house.getCost()) return;

        for (MarketKeyHouse keyHouse : this.keyHouses) {
            if (keyHouse.equals(house)) {

                // Wait for thread to be ready so we wont get a ConcurrentModificationException
                Platform.runLater(() -> {
                    try {
                        localPlayer.getPlayerBoard().addHouse((KeyHouse) keyHouse);
                        localPlayer.getPlayerBoard().payCoin(keyHouse.getCost());

                        this.keyHouses.remove(keyHouse);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        this.updateObserver();
    }

    /**
     * Move a local remote house to the playerboard of the given gameClient
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param house
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public synchronized void buyRemoteStarHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException {
        Player localPlayer = this.getLocalPlayer(gameClient);

        if (localPlayer.getPlayerBoard().getCoins() < house.getCost()) return;

        for (MarketStarHouse starHouse : this.starHouses) {
            if (starHouse.equals(house)) {

                // Wait for thread to be ready so we wont get a ConcurrentModificationException
                Platform.runLater(() -> {
                    try {
                        localPlayer.getPlayerBoard().addHouse((StarHouse) starHouse);
                        localPlayer.getPlayerBoard().payCoin(starHouse.getCost());

                        this.starHouses.remove(starHouse);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        this.updateObserver();
    }

    /**
     * Refresh all the outposts
     *
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public void refreshHousesAndOutposts() throws RemoteException {

        // Add the buildings back to the available pool
        this.houses.addAll(Arrays.asList(this.availableHouses));
        this.outposts.addAll(Arrays.asList(this.availableOutposts));

        for (int i = 0; i < 4; i++) {
            this.availableHouses[i] = this.randomHouse();
            this.availableOutposts[i] = this.randomOutpost();
        }
        this.updateObserver();
    }


    /**
     * Move villagers to the left if it is available and add new villagers for null spaces.
     *
     * @throws RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public void reorderAvailableVillagers() throws RemoteException {

        boolean foundNull = false;
        int nullIndex = 0;

        for (int i = this.availableVillagers.length - 1; i >= 0; i--) {
            Villager villager = this.availableVillagers[i];


            if (villager == null && nullIndex < i) {
                foundNull = true;
                nullIndex = i;
            } else if (villager != null && foundNull) {

                this.availableVillagers[nullIndex] = villager;
                this.availableVillagers[i] = null;

                // Reset the loop back to the nullIndex
                i = nullIndex;

                foundNull = false;
                nullIndex = 0;
            }
        }

        int index = 0;

        do {
            availableVillagers[index] = this.randomVillager();
            index++;
        } while (index < this.availableVillagers.length &&  this.availableVillagers[index] == null);

        this.updateObserver();

    }

    private void updateObserver() throws RemoteException {
        this.gameController.getServer().notifyClients(new MarketUpdateNotification(this));
    }


    public static void main(String[] args) {
        String[] availableVillagers = new String[5];

        boolean foundNull = false;
        int nullIndex = 0;

        for (int i = availableVillagers.length - 1; i >= 0; i--) {
            String villager = availableVillagers[i];

            if (villager == null && nullIndex < i) {
                foundNull = true;
                nullIndex = i;

            } else if (villager != null && foundNull) {

                availableVillagers[nullIndex] = villager;
                availableVillagers[i] = null;

                nullIndex = 0;
                foundNull = false;

                i = availableVillagers.length;
            }
        }


        // Refill the villagers with random villagers
        int index = 0;

        do {

            availableVillagers[index] = "Random shizzle";

        } while (availableVillagers[++index % availableVillagers.length] == null);

        for (String availableVillager : availableVillagers) {

            System.out.println(availableVillager);

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
