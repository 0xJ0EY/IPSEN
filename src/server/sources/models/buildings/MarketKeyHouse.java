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

    /**
     * For creating a KeyHouse object.
     * @param price
     * @param perks
     * */
    public MarketKeyHouse(int price, ArrayList<Perk> perks){
        super(price, perks);
    }

    @Override
    public void buy(MarketInterface market, GameClientInterface gameClient) throws RemoteException {
        market.buyRemoteKeyHouse(gameClient, this);
    }
}
