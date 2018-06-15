package server.sources.models;

import server.sources.interfaces.*;
import server.sources.models.buildings.StarHouse;
import server.sources.models.goods.*;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.models.perks.*;
import server.sources.models.buildings.Building;
import server.sources.models.perks.Perk;
import server.sources.models.villagers.*;
import server.sources.notifications.MessageNotification;
import server.sources.notifications.UpdatePlayerBoardNotification;
import server.sources.strategies.villagers.AddVillagerStrategy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlayerBoard extends UnicastRemoteObject implements PlayerBoardInterface {

    private static final long serialVersionUID = 1337L;

    private final int MAX_REPUTATION = 8;
    private final int MIN_REPUTATION = -2;

    private EndOfRound endOfRound = new EndOfRound(this);

    private PlayerInterface player;

    private ArrayList<Villager> villagers = new ArrayList<>();
    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<Outpost> outposts = new ArrayList<>();
    private ArrayList<Good> goods = new ArrayList<>();
    private ArrayList<Building> harvestBuildings;

    private AdvancementTracker advancementTracker = new AdvancementTracker(this);

    private int ciders = 0;
    private int potions = 0;
    private int coins = 10;
    private int beds = 3;
    private int caveCards = 0;
    private int rerolls = 0;

    private int reputation = 0;

    private boolean hasTrainToReadyPerk = false;
    private boolean hasCoinForBuildPerk = false;
    private boolean hasCoinForExplorePerk = false;

    public PlayerBoard(PlayerInterface player) throws RemoteException {
        this.player = player;


        // Trainer
        ArrayList<Lantern> lanternsTrainer = new ArrayList<Lantern>();
        lanternsTrainer.add(new Lantern(2, 1));

        villagers.add(new TrainerVillager(lanternsTrainer, Villager.VillagerState.USABLE, "villager_background_01.png"));

        // Builder
        ArrayList<Lantern> lanternsBuilder = new ArrayList<Lantern>();
        lanternsBuilder.add(new Lantern(4, 1));

        villagers.add(new TrainerVillager(lanternsBuilder, Villager.VillagerState.USABLE, "villager_background_02.png"));

        // Normal
        ArrayList<Lantern> lanternsNormal = new ArrayList<Lantern>();
        lanternsNormal.add(new Lantern(1, 1));
        lanternsNormal.add(new Lantern(3, 2));

        villagers.add(new Villager(lanternsNormal, Villager.VillagerState.USABLE, "villager_background_03.png"));


        for (VillagerInterface villager : villagers) {
            villager.setPlayerBoard(this);
        }

        ArrayList<Perk> perks = new ArrayList<Perk>();
        perks.add(new BedPerk());
        perks.add(new BedPerk());
        perks.add(new BedPerk());

        this.houses.add(new House(0, perks, "house_icon.png"));

    }

    @Override
    public void addCider() throws RemoteException {
        this.ciders += 1;
        this.updateObserver();
    }

    @Override
    public void addPotion() throws RemoteException {
        this.potions += 1;
        this.updateObserver();
    }

    public void payCoin(int coin) throws RemoteException {
        this.coins -= coin;
        this.updateObserver();
    }

    public void addGood(Good good) throws RemoteException {
        this.goods.add(good);
        this.updateObserver();
    }

    @Override
    public void updateVillager() throws RemoteException {
        this.updateObserver();
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
    public boolean hasCaveCards() throws RemoteException {
        return this.caveCards > 0;
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
     * @author Jan Douwe Sminia | Joey de Ruiter
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
     * @author Jan Douwe Sminia | Joey de Ruiter
     * @return ArrayList of available builder villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<VillagerInterface> listAvailableBuilderVillagers() throws RemoteException {
        ArrayList<VillagerInterface> builders = new ArrayList<VillagerInterface>();

        for (VillagerInterface villager : this.listAvailableVillagers()) {
            if (villager instanceof Buildable) {
                builders.add(villager);
            }
        }

        return builders;
    }

    /**
     * List all available trainer villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia | Joey de Ruiter
     * @return ArrayList of all available villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public ArrayList<VillagerInterface> listAvailableTrainerVillagers() throws RemoteException {
        ArrayList<VillagerInterface> trainers = new ArrayList<VillagerInterface>();

        for (VillagerInterface villager : this.listAvailableVillagers()) {
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
        villager.setPlayerBoard(this);
        if (!this.hasTrainToReadyPerk) villager.tire();

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
        this.activatePerks(house);
        this.houses.add(house);
        this.updateObserver();
    }

    @Override
    public ArrayList<Outpost> getOutposts()throws RemoteException {
        return this.outposts;
    }

    public void addOutpost(Outpost outpost) throws RemoteException {
        this.activatePerks(outpost);
        this.outposts.add(outpost);
        this.updateObserver();
    }

    private void activatePerks(Building building) throws RemoteException{
        for (Perk perk : building.listPerks()) {
            perk.setGameClient(this.player.getGameClient());
            if (perk instanceof ActivateOnObtainedInterface) {
                ((ActivateOnObtainedInterface) perk).activateOnObtainedPerk();
            }
        }
    }

    @Override
    public ArrayList<Good> getGoods() throws RemoteException {
        return this.goods;
    }

    @Override
    public void goodSold(int index) throws RemoteException{
        goods.remove(index);
        this.updateObserver();

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

        int coins = this.coins;
        this.endOfRound.load();

        // Recalculate available beds
        this.beds = this.endOfRound.countBeds();

        // Add all coins
        this.addCoins(this.endOfRound.countCoins());

        // clears the rerolls
        this.clearRerolls();

        // Refresh perks
        this.endOfRound.refreshPerks();

        // Reset all villagers
        for (Villager villager : this.villagers) {
            villager.endOfRound();
        }

        this.updateObserver();

        int difference = this.coins - coins;

        this.player.getGameClient().receiveNotification(
            new MessageNotification(
                String.format("You have received %s coins", difference)
            )
        );
    }

    private void updateObserver() throws RemoteException {
        try {
            this.player.getGameClient().receiveNotification(new UpdatePlayerBoardNotification(this));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int countHarvestableBuildings() throws RemoteException {
        int count = 0;

        ArrayList<Building> buildings = new ArrayList<Building>();
        buildings.addAll(this.houses);
        buildings.addAll(this.outposts);

        try {
            for (Building building: buildings) {

                for (Perk perk : building.listPerks()) {
                    if (perk instanceof Harvestable && ((Harvestable) perk).canHarvest()) {
                        count += ((Harvestable) perk).getAmountLeft();
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public ArrayList<BuildingInterface> getHarvestBuildings() throws RemoteException {
        this.checkHarvestBuildings();
        ArrayList<BuildingInterface> buildings = new ArrayList<BuildingInterface>();

        for (Building harvestBuilding : this.harvestBuildings) {
            buildings.add((BuildingInterface) harvestBuilding);
        }

        return buildings;
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
                    if (perk instanceof Harvestable && ((Harvestable) perk).canHarvest()) {
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
    public ArrayList<BuildingInterface> getBuildings() throws RemoteException {
        ArrayList<BuildingInterface> buildings = new ArrayList<BuildingInterface>();
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

    public int getCaveCards(){
        return this.caveCards;
    }

    @Override
    public void obtainedTrainToReadyPerk() throws RemoteException{
        this.hasTrainToReadyPerk = true;
    }

    @Override
    public void obtainedCoinForBuildPerk() throws RemoteException{
        this.hasCoinForBuildPerk = true;
    }

    @Override
    public boolean getHasCoinForBuildPerk() throws RemoteException{
        return this.hasCoinForBuildPerk;
    }

    @Override
    public void obtainedCoinForExplorePerk() throws RemoteException{
        this.hasCoinForBuildPerk = true;
    }

    @Override
    public boolean getHasCoinForExplorePerk() throws RemoteException{
        return this.hasCoinForExplorePerk;
    }

    @Override
    public void addReroll() throws RemoteException {
        this.rerolls++;
    }

    private void clearRerolls() {
        this.rerolls = 0;
    }

    @Override
    public PlayerInterface getPlayer() throws RemoteException {
        return this.player;
    }

    @Override
    public void changeReputation(int amount) throws RemoteException {

        // Clamp reputation
        this.reputation = Math.max(
            this.MIN_REPUTATION,
            Math.max(
                this.MAX_REPUTATION,
                this.reputation + amount
            )
        );
    }

    @Override
    public int getReputation() throws RemoteException {
        return this.reputation;
    }
}
