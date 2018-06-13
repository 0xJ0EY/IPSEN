package server.sources.models;


import server.sources.interfaces.AdvancementTrackerInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.buildings.StarHouse;
import server.sources.models.goods.*;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.models.perks.*;
import server.sources.models.buildings.Building;
import server.sources.models.goods.*;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.models.perks.Perk;
import server.sources.models.villagers.*;
import server.sources.notifications.UpdatePlayerBoardNotification;
import server.sources.strategies.villagers.AddVillagerStrategy;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerBoard extends UnicastRemoteObject implements PlayerBoardInterface {

    private static final long serialVersionUID = 1337L;

    private EndOfRound endOfRound = new EndOfRound(this);

    private PlayerInterface player;

    private ArrayList<Villager> villagers = new ArrayList<>();
    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<Outpost> outposts = new ArrayList<>();
    private ArrayList<Good> goods = new ArrayList<>();
    private ArrayList<Building> harvestBuildings;

    private AdvancementTracker advancementTracker = new AdvancementTracker(this);

    private int ciders = 2;
    private int potions = 2;
    private int coins = 10;
    private int beds = 3;
    private int caveCards = 0;

    public PlayerBoard(PlayerInterface player) throws RemoteException {
        this.player = player;

        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

        lanterns.add(new Lantern(3, 2));
        lanterns.add(new Lantern(4, 4));

        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.USABLE, "villager_background_01.png"));
        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.USABLE, "villager_background_02.png"));

        // TODO: A nice implementation of this
        villagers.add(new BuilderVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.USABLE, "villager_background_03.png"));
        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.INJURED, "villager_background_04.png"));
        villagers.add(new Villager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.TIRED, "villager_background_05.png"));

        /**
         * This is only for testing scoreboard.
         * @author Robin Silverio
         */
        ArrayList<Perk> perks_1 = new ArrayList<Perk>();
        perks_1.add(new CiderPerk(1));
        perks_1.add(new CoinPerk(2));
        perks_1.add(new VillagePointsPerk(3));
        houses.add(new House(2, perks_1, "house_0.png"));

        ArrayList<Perk> perks_2 = new ArrayList<Perk>();
        perks_2.add(new CiderPerk(1));
        perks_2.add(new CoinPerk(2));
        perks_2.add(new VillagePointsPerk(3));
        houses.add(new StarHouse(2, perks_2, "house_0.png"));


        for (int i = 0; i < 5; i++) {
            this.goods.add(new FruitGood());
        }

        for (int i = 0; i < 5; i++) {
            this.goods.add(new AmethystGood());
        }

        for (int i = 0; i < 5; i++) {
            this.goods.add(new PaperGood());
        }

        for (VillagerInterface villager : villagers) {
            villager.setPlayerBoard(this);
        }

        ArrayList<Perk> perks = new ArrayList<Perk>();
        perks.add(new BedPerk());
        perks.add(new BedPerk());
        perks.add(new BedPerk());

        this.houses.add(new House(0, perks, "house_0.png"));

    }

    @Override
    public void addCider() throws RemoteException {
        this.ciders += 1;
    }

    @Override
    public void addPotion() throws RemoteException {
        this.potions += 1;
    }

    public void payCoin(int coin) throws RemoteException {
        this.coins -= coin;
        this.updateObserver();
    }

    public void addGood(Good good){
        this.goods.add(good);
    }

    /**
     * This is for listing villagers on playerboard when player enters a game environment.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public boolean hasCider() throws RemoteException {
        return this.ciders > 0;
    }

    @Override
    public boolean hasPotion() throws RemoteException {
        return this.potions > 0;
    }

    @Override
    public boolean hasBeds() throws RemoteException {
        return this.beds > 0;
    }

    @Override
    public void useCider() throws RemoteException {
        this.ciders--;
        this.updateObserver();
    }

    @Override
    public void usePotion() throws RemoteException {
        this.potions--;
        this.updateObserver();
    }

    @Override
    public void useBed() throws RemoteException {
        this.beds--;
        this.updateObserver();
    }

    /**
     * List all villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia
     * @return ArrayList of all villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<VillagerInterface> listVillagers() throws RemoteException {
        ArrayList<VillagerInterface> villagers = new ArrayList<VillagerInterface>();

        for (VillagerInterface villager : this.villagers) {
            villagers.add(villager);
        }

        return villagers;
    }

    /**
     * List all available villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia
     * @return ArrayList of all available villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<VillagerInterface> listAvailableVillagers() throws RemoteException {
        ArrayList<VillagerInterface> usableVillagers = new ArrayList<VillagerInterface>();

        for (VillagerInterface villager : this.villagers) {
            if (villager.isUsable()){
                usableVillagers.add(villager);
            }
        }

        return usableVillagers;
    }

    /**
     * List all available builder villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia
     * @return ArrayList of available builder villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<VillagerInterface> listAvailableBuilderVillagers() throws RemoteException {
        ArrayList<VillagerInterface> builders = new ArrayList<VillagerInterface>();

        for (VillagerInterface villager : this.villagers) {
            if (villager instanceof Buildable) {
                builders.add(villager);
            }
        }

        return builders;
    }

    /**
     * List all available trainer villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia
     * @return ArrayList of all available villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<VillagerInterface> listAvailableTrainerVillagers() throws RemoteException {
        ArrayList<VillagerInterface> trainers = new ArrayList<VillagerInterface>();

        for (VillagerInterface villager : this.villagers) {
            if (villager instanceof Trainable) {
                trainers.add(villager);
            }
        }

        return trainers;
    }

    /**
     * Add a local villager to the playerboard.
     *
     * @author Joey de Ruiter
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public void addVillager(Villager villager) throws RemoteException {
        villager.tire();
        villager.setPlayerBoard(this);

        villagers.add(villager);
        this.updateObserver();
    }

    /**
     * Execute the villagerStrategy to add special villagers from the rewards.
     *
     * @author Joey de Ruiter | Richard Kerkvliet
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public void executeVillagerStrategy(AddVillagerStrategy villagerStrategy) throws RemoteException {
        villagerStrategy.execute(this);
    }

    @Override
    public void addCoins(int amount) throws RemoteException {
        if (amount < 0) return;
        this.coins += amount;
        this.updateObserver();
    }

    @Override
    public ArrayList<House> getHouses() throws RemoteException{
        return this.houses;
    }

    public void addHouse(House house) throws RemoteException {
        this.houses.add(house);
        this.updateObserver();
    }

    @Override
    public ArrayList<Outpost> getOutposts()throws RemoteException {
        return this.outposts;
    }

    public void addOutpost(Outpost outpost) throws RemoteException {
        this.outposts.add(outpost);
        this.updateObserver();
    }

    @Override
    public ArrayList<Good> getGoods() throws RemoteException {
        return this.goods;
    }

    /**
     * Get current amount of coins
     *
     * Khajiit  has wares, if you have coin
     * @return int of current amount
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public int getCoins() throws RemoteException {
        return this.coins;
    }

    public int getPotions() throws RemoteException {
        return this.potions;
    }

    public int getBeds() throws RemoteException {
        return this.beds;
    }

    public int getCiders() throws RemoteException {
        return this.ciders;
    }

    /**
     * Return the AdvancementTracker corresponding with the current playerboard.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException java.rmi.RemoteException
     */
    public AdvancementTrackerInterface getAdvancementTracker() throws RemoteException {
        return this.advancementTracker;
    }


    /**
     * Process the end of round sequence,
     * this will give back rewards,
     * reset villagers so they can sleep again.
     *
     * @author Joey de Ruiter
     * @throws RemoteException java.rmi.RemoteException
     */
    public void endOfRound() throws RemoteException {

        this.endOfRound.load();

        // Recalculate available beds
        this.beds = this.endOfRound.countBeds();

        // Add all coins
        this.addCoins(this.endOfRound.countCoins());

        // Refresh perks
        this.endOfRound.refreshPerks();

        // Reset all villagers
        for (Villager villager : this.villagers) {
            villager.endOfRound();
        }

        this.updateObserver();
    }

    private void updateObserver() throws RemoteException {
        try {
            this.player.getGameClient().receiveNotification(new UpdatePlayerBoardNotification(this));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Building> getHarvestBuildings() {
        this.checkHarvestBuildings();
        return this.harvestBuildings;
    }

    private void checkHarvestBuildings() {
        this.harvestBuildings = new ArrayList<>();

        ArrayList<Building> buildings = new ArrayList<Building>();
        buildings.addAll(this.houses);
        buildings.addAll(this.outposts);

        try {
            for (Building building: buildings) {
                boolean harvestable = false;

                for (Perk perk : building.listPerks()) {
                    if (perk instanceof Harvestable) {
                        harvestable = true;
                    }
                }

                if (harvestable) {
                    this.harvestBuildings.add(building);
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Perk> getBuildingsPerks() throws RemoteException {
        ArrayList<Perk> perks = new ArrayList<Perk>();

        ArrayList<Building> buildings = new ArrayList<Building>();
        buildings.addAll(this.houses);
        buildings.addAll(this.outposts);

        // Get all perks from both houses and outposts
        if (buildings.size() > 0) {
            for (Building building : buildings) {
                perks.addAll(building.listPerks());
            }
        }

        return perks;
    }

    @Override
    public ArrayList<Building> getBuildings() throws RemoteException {
        ArrayList<Building> buildings = new ArrayList<Building>();
        buildings.addAll(this.houses);
        buildings.addAll(this.outposts);

        return buildings;
    }

    @Override
    public void moveGoodToAdvancementTracker(int index) throws RemoteException {
        Good good = this.goods.get(index);
        this.getAdvancementTracker().addGood(good);

        this.goods.remove(index);

        this.updateObserver();
    }

    public void addCaveCard(){
        this.caveCards++;
    }
}
