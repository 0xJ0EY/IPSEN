package server.sources.interfaces;

import server.sources.models.goods.Good;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Remote interface so the AdvancementTracker can be a remote object on the server.
 * @author Joey de Ruiter
 */
public interface AdvancementTrackerInterface extends Remote, Serializable {

    /**
     * Add a good to the AdvancementTracker
     *
     * @param good
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void addGood(Good good) throws RemoteException;

    /**
     * Return all the tokens set in the AdvancementTracker
     *
     * @return Map<Good, Integer>
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public Map<Good, Integer> getTokens() throws RemoteException;

    /**
     * Return the amount of points the AdvancementTracker is currently worth
     *
     * @return total amount of points
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public int calculatePoints()  throws RemoteException;

    /**
     * Return the points a good on the specified index is worth
     *
     * @param index
     * @return points by index
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public int getPointsByIndex(int index) throws RemoteException;

    /**
     * Return the amount of coins that the AdvancementTracker generates every round.
     *
     * @return coins
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public int calculateCoins() throws RemoteException;

}
