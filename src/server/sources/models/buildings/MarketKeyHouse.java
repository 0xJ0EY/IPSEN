package server.sources.models.buildings;

import server.sources.interfaces.BuildingMarketInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.MarketInterface;
import server.sources.models.perks.Perk;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class MarketKeyHouse extends KeyHouse implements BuildingMarketInterface {

    public MarketKeyHouse(int cost, ArrayList<Perk> perks, String background) {
        super(cost, perks, background);
    }

    @Override
    public void buy(MarketInterface market, GameClientInterface gameClient) throws RemoteException {
        market.buyRemoteKeyHouse(gameClient, this);
    }
}
