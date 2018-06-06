package server.sources.interfaces;

import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.models.villagers.Villager;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PlayerBoardControllerInterface extends Remote, Serializable {

    public ArrayList<Villager> listAvailableVillagers() throws RemoteException;

    public ArrayList<Villager> listAvailableBuilderVillagers() throws RemoteException;

    public ArrayList<Villager> listAvailableTrainerVillagers() throws RemoteException;

    public Villager getVillager(int index) throws RemoteException;

    public void addVillager(Villager villager) throws RemoteException;

    public void addCoins(int amount) throws RemoteException;

    public ArrayList<House> getHouses() throws RemoteException;

    public ArrayList<Outpost> getOutposts() throws RemoteException;

    /**
     * Get current amout of coins
     *
     * Khajiit  has wares, if you have coin
     * @return int of current amount
     * @throws RemoteException
     */
    public int getCoins() throws RemoteException;

    public void addCider() throws RemoteException;
}
