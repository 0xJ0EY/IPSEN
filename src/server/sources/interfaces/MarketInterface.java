package server.sources.interfaces;

import client.source.Client;
import server.sources.controllers.GoodOnSale;
import server.sources.models.buildings.*;
import server.sources.models.goods.Good;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MarketInterface extends Remote, Serializable {

    public VillagerInterface[] listAvailableVillagers() throws RemoteException;

    public void buyRemoteVillager(GameClientInterface gameClient, VillagerInterface villagerInterface) throws RemoteException;

    public ArrayList<MarketHouse> listAvailableHouses() throws RemoteException;

    public ArrayList<MarketKeyHouse> listAvailableKeyHouses() throws RemoteException;

    public ArrayList<MarketStarHouse> listAvailableStarHouses() throws RemoteException;

    public ArrayList<MarketOutpost> listAvailableOutposts() throws RemoteException;

    public void buyRemoteHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException;

    public void buyRemoteOutpost(GameClientInterface gameClient, BuildingInterface outpost) throws RemoteException;

    public void buyRemoteKeyHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException;

    public void buyRemoteStarHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException;

    public void sellGood(Good good, Client client) throws RemoteException;

    public ArrayList<GoodOnSale> getGoodList() throws RemoteException;

}
