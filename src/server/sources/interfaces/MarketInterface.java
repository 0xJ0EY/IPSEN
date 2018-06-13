package server.sources.interfaces;

import server.sources.models.buildings.*;
import server.sources.models.goods.Good;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Remote interface so the Market can be a remote object on the server.
 * @author Joey de Ruiter
 */
public interface MarketInterface extends Remote, Serializable {

    /**
     * List all available villagers to be purchased.
     *
     * @author Richard Kerkvliet
     * @return VillagerInterface[]
     * @throws RemoteException java.rmi.RemoteException
     */
    public VillagerInterface[] listAvailableVillagers() throws RemoteException;

    /**
     * List all available houses to be purchased.
     *
     * @author Joey de Ruiter
     * @return ArrayList
     * @throws RemoteException java.rmi.RemoteException
     */
    public ArrayList<MarketHouse> listAvailableHouses() throws RemoteException;

    /**
     * List all available key houses listed for purchased.
     *
     * @author Joey de Ruiter
     * @return ArrayList
     * @throws RemoteException java.rmi.RemoteException
     */
    public ArrayList<MarketKeyHouse> listAvailableKeyHouses() throws RemoteException;

    /**
     * List all available star houses listed for purchase.
     *
     * @author Joey de Ruiter
     * @return ArrayList
     * @throws RemoteException java.rmi.RemoteException
     */
    public ArrayList<MarketStarHouse> listAvailableStarHouses() throws RemoteException;

    /**
     * List all available outposts to be purchased.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException java.rmi.RemoteException
     */
    public ArrayList<MarketOutpost> listAvailableOutposts() throws RemoteException;

    /**
     * Move a local villager object to the villagerInterface.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param villagerInterface
     * @throws RemoteException java.rmi.RemoteException
     */
    public void buyRemoteVillager(GameClientInterface gameClient, VillagerInterface villagerInterface) throws RemoteException;

    /**
     * Move a local house to the playerboard of the given gameClient.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param house
     * @throws RemoteException java.rmi.RemoteException
     */
    public void buyRemoteHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException;

    /**
     * Move a local outpost to the playerboard of the given gameClient.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param outpost
     * @throws RemoteException java.rmi.RemoteException
     */
    public void buyRemoteOutpost(GameClientInterface gameClient, BuildingInterface outpost) throws RemoteException;

    /**
     * Move a local key house to the playerboard of the given gameClient.
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param house
     * @throws RemoteException java.rmi.RemoteException
     */
    public void buyRemoteKeyHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException;

    /**
     * Move a local remote house to the playerboard of the given gameClient
     *
     * @author Joey de Ruiter
     * @param gameClient
     * @param house
     * @throws RemoteException java.rmi.RemoteException
     */
    public void buyRemoteStarHouse(GameClientInterface gameClient, BuildingInterface house) throws RemoteException;

    /**
     * Refresh all the outposts
     *
     * @throws RemoteException java.rmi.RemoteException
     */
    public void refreshHousesAndOutposts() throws RemoteException;

    /**
     * Move villagers to the left if it is available and add new villagers for null spaces.
     *
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void reorderAvailableVillagers() throws RemoteException;

}
