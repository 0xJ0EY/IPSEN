package server.sources.interfaces;

import server.sources.models.goods.Good;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface AdvancementTrackerInterface extends Remote, Serializable {

    public void addGood(Good good) throws RemoteException;

    public Map<Good, Integer> getTokens() throws RemoteException;

    public int calculatePoints()  throws RemoteException;

    public int calculateCoins() throws RemoteException;

}
