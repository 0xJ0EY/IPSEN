package server.sources.interfaces;

import java.rmi.RemoteException;


/**
 * Interface to buy local houses from the market and add them to the local gameClient
 * @author Joey de Ruiter
 */
public interface BuildingMarketInterface extends BuildingInterface {

    /**
     * Method to buy local houses from the market
     *
     * @param market
     * @param gameClient
     * @throws RemoteException java.rmi.RemoteException
     */
    public void buy(MarketInterface market, GameClientInterface gameClient) throws RemoteException;

}
