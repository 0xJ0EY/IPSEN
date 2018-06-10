package server.sources.models;


import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.buildings.Building;
import server.sources.models.goods.*;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.models.perks.BedPerk;
import server.sources.models.perks.Harvestable;
import server.sources.models.perks.Perk;
import server.sources.models.villagers.*;
import server.sources.notifications.UpdatePlayerBoardNotification;
import server.sources.strategies.villagers.AddVillagerStrategy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlayerBoard extends UnicastRemoteObject implements PlayerBoardInterface {

    private static final long serialVersionUID = 1337L;

    private EndOfRound endOfRound = new EndOfRound(this);

    private PlayerInterface player;

    private ArrayList<Villager> villagers = new ArrayList<>();
    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<Outpost> outposts = new ArrayList<>();
    private ArrayList<Good> goods = new ArrayList<>();
    private ArrayList<Building> harvestBuildings;

    private int ciders = 2;
    private int potions = 2;
    private int coins = 10;
    private int beds = 3;

    public PlayerBoard(PlayerInterface player) throws RemoteException {
        this.player = player;

        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

        lanterns.add(new Lantern(3, 2));
        lanterns.add(new Lantern(4, 4));

        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.USABLE));
        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.USABLE));

        // TODO: A nice implementation of this
        villagers.add(new BuilderVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.USABLE));
        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.INJURED));
        villagers.add(new Villager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.TIRED));

        for (VillagerInterface villager : villagers) {
            villager.setPlayerBoard(this);
        }

        ArrayList<Perk> perks = new ArrayList<Perk>();
        perks.add(new BedPerk());
        perks.add(new BedPerk());
        perks.add(new BedPerk());

        this.houses.add(new House(0, perks));

    }

    @Override
    public void addCider() throws RemoteException {
        this.ciders += 1;
    }

    @Override
    public void addPotion() throws RemoteException {
        this.potions += 1;
    }

    public void payCoin(int coin){
        this.coins -= coin;
    }

    public void addGood(Good good){
        this.goods.add(good);
    }

    /**
     * This is for listing villagers on playerboard when player enters a game environment.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException
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

    @Override
    public ArrayList<VillagerInterface> listVillagers() throws RemoteException {
        ArrayList<VillagerInterface> villagers = new ArrayList<VillagerInterface>();

        for (VillagerInterface villager : this.villagers) {
            villagers.add(villager);
        }

        return villagers;
    }

    /**
     * This is for listing still available villagers on playerboard after an action is made.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException
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
     * This is for listing available villagers on playerboard, only to use for build action.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException
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
     * This is for listing available villagers on playerboard, only to use for train action.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException
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

    @Override
    public VillagerInterface getVillager(int index) throws RemoteException {
        return villagers.get(index);
    }

    /**
     * This is for adding new trained villagers to playerboard.
     * @param villager
     * @throws RemoteException
     */
    @Override
    public void addVillager(Villager villager) throws RemoteException {
        villager.tire();
        villager.setPlayerBoard(this);

        villagers.add(villager);
        this.updateObserver();
    }

    @Override
    public void executeVillagerStrategy(AddVillagerStrategy villagerStrategy) throws RemoteException {
        villagerStrategy.execute(this);
    }

    @Override
    public void addCoins(int amount) throws RemoteException {
        if (amount > 0) return;
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

    public ArrayList<Good> getGoods(){
        return this.goods;
    }

    /**
     * Get current amount of coins
     *
     * Khajiit  has wares, if you have coin
     * @return int of current amount
     * @throws RemoteException
     */
    @Override
    public int getCoins() {
        return this.coins;
    }

    public int getPotions() {
        return this.potions;
    }

    public int getBeds() {
        return this.beds;
    }

    public int getCiders() {
        return this.ciders;
    }

    public void endOfRound() throws RemoteException {
        // Recalculate available beds
        this.beds = endOfRound.countBeds();

        // Reset all villagers
        for (Villager villager : this.villagers) {
            villager.endOfRound();
        }

        this.updateObserver();
    }

    public void updateObserver() throws RemoteException {
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
}
