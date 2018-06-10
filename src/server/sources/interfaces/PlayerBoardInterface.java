package server.sources.interfaces;

import server.sources.models.buildings.Building;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.models.goods.Good;
import server.sources.models.villagers.Villager;
import server.sources.strategies.villagers.AddVillagerStrategy;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PlayerBoardInterface extends Remote, Serializable {

    public ArrayList<VillagerInterface> listVillagers() throws RemoteException;

    public ArrayList<VillagerInterface> listAvailableVillagers() throws RemoteException;

    public ArrayList<VillagerInterface> listAvailableBuilderVillagers() throws RemoteException;

    public ArrayList<VillagerInterface> listAvailableTrainerVillagers() throws RemoteException;

    public VillagerInterface getVillager(int index) throws RemoteException;

    public void addVillager(Villager villager) throws RemoteException;

    public void executeVillagerStrategy(AddVillagerStrategy villagerStrategy) throws RemoteException;

    public void addCoins(int amount) throws RemoteException;

    public void addCider() throws RemoteException;

    public void addGood(Good good) throws RemoteException;

    public void addPotion() throws RemoteException;


    /**
     * Get current amout of coins
     *
     * Khajiit  has wares, if you have coin
     * @return int of current amount
     * @throws RemoteException
     */
    public int getCoins() throws RemoteException;

    public int getCiders() throws RemoteException;

    public int getPotions() throws RemoteException;

    public int getBeds() throws RemoteException;

    public ArrayList<Good> getGoods() throws RemoteException;

    public void payCoin(int price) throws RemoteException;

    public void addHouse(House building) throws RemoteException;

    public ArrayList<House> getHouses() throws RemoteException;

    public void addOutpost(Outpost building) throws RemoteException;

    public ArrayList<Outpost> getOutposts() throws RemoteException;

    public boolean hasCider() throws RemoteException;

    public boolean hasPotion() throws RemoteException;

    public boolean hasBeds() throws RemoteException;

    public void useCider() throws RemoteException;

    public void usePotion() throws RemoteException;

    public void useBed() throws RemoteException;

    public ArrayList<Building> getHarvestBuildings() throws RemoteException;

    public void endOfRound() throws RemoteException;

    public void updateObserver() throws RemoteException;

}
