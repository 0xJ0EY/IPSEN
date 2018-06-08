package server.sources.interfaces;

import server.sources.models.GameClient;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MarketInterface extends Remote, Serializable {

    public VillagerInterface[] listAvailableVillagers() throws RemoteException;

    public void buyRemoteVillager(GameClientInterface gameClient, VillagerInterface villagerInterface) throws RemoteException;

}
