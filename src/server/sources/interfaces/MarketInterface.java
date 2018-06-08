package server.sources.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MarketInterface extends Remote, Serializable {

    public VillagerInterface[] listAvailableVillagers() throws RemoteException;
}
