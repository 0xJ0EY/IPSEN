package server.sources.interfaces;

import server.sources.models.GameClient;

import java.rmi.RemoteException;

public interface BuildingMarketInterface extends BuildingInterface {

    public void buy(MarketInterface market, GameClientInterface gameClient) throws RemoteException;

}
