package server.sources.interfaces;

import server.sources.models.AdvancementTracker;
import server.sources.models.buildings.Building;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.models.goods.Good;
import server.sources.models.perks.Perk;
import server.sources.models.villagers.Villager;
import server.sources.strategies.villagers.AddVillagerStrategy;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


/**
 * Remote interface so the PlayerBoard can be a remote object on the server.
 */
public interface PlayerBoardInterface extends Remote, Serializable {

    /**
     * List all villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia
     * @return ArrayList of all villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    public ArrayList<VillagerInterface> listVillagers() throws RemoteException;

    /**
     * List all available villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia
     * @return ArrayList of all available villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    public ArrayList<VillagerInterface> listAvailableVillagers() throws RemoteException;

    /**
     * List all available builder villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia
     * @return ArrayList of all available villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    public ArrayList<VillagerInterface> listAvailableBuilderVillagers() throws RemoteException;

    /**
     * List all available trainer villagers of on the playerboard.
     *
     * @author Jan Douwe Sminia
     * @return ArrayList of all available villagers
     * @throws RemoteException java.rmi.RemoteException
     */
    public ArrayList<VillagerInterface> listAvailableTrainerVillagers() throws RemoteException;

    /**
     * Add a local villager to the playerboard.
     *
     * @author Joey de Ruiter
     * @throws RemoteException java.rmi.RemoteException
     */
    public void addVillager(Villager villager) throws RemoteException;

    /**
     * Execute the villagerStrategy to add special villagers from the rewards.
     *
     * @author Joey de Ruiter | Richard Kerkvliet
     * @throws RemoteException java.rmi.RemoteException
     */
    public void executeVillagerStrategy(AddVillagerStrategy villagerStrategy) throws RemoteException;

    /**
     * Return the AdvancementTracker corresponding with the current playerboard.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException java.rmi.RemoteException
     */
    public AdvancementTrackerInterface getAdvancementTracker() throws RemoteException;

    public void addCoins(int amount) throws RemoteException;

    public void addCider() throws RemoteException;

    public void addGood(Good good) throws RemoteException;

    public void addPotion() throws RemoteException;

    public void addCaveCard() throws RemoteException;

    public int getCoins() throws RemoteException;

    public int getCiders() throws RemoteException;

    public int getPotions() throws RemoteException;

    public int getBeds() throws RemoteException;

    public ArrayList<Good> getGoods() throws RemoteException;

    public void goodSold(int index) throws RemoteException;

    public void payCoin(int price) throws RemoteException;

    public void addHouse(House building) throws RemoteException;

    public ArrayList<House> getHouses() throws RemoteException;

    public void addOutpost(Outpost building) throws RemoteException;

    public ArrayList<Outpost> getOutposts() throws RemoteException;

    public boolean hasCider() throws RemoteException;

    public boolean hasPotion() throws RemoteException;

    public boolean hasBeds() throws RemoteException;

    public boolean hasCaveCards() throws RemoteException;

    public void useCider() throws RemoteException;

    public void usePotion() throws RemoteException;

    public void useBed() throws RemoteException;

    public ArrayList<BuildingInterface> getHarvestBuildings() throws RemoteException;

    /**
     * Process the end of round sequence,
     * this will give back rewards,
     * reset villagers so they can sleep again.
     *
     * @author Joey de Ruiter
     * @throws RemoteException java.rmi.RemoteException
     */
    public void endOfRound() throws RemoteException;

    public ArrayList<Perk> getBuildingsPerks() throws RemoteException;

    public ArrayList<BuildingInterface> getBuildings() throws RemoteException;

    public void moveGoodToAdvancementTracker(int index) throws RemoteException;

    public void updateVillager() throws RemoteException;
}
