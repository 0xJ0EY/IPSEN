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
public class MarketStarHouse extends StarHouse implements BuildingMarketInterface {

    /**
     * For creating a StarHouse object.
     * @param price
     * @param perks
     * */
    public MarketStarHouse(int price, ArrayList<Perk> perks){
        super(price, perks);
    }

    @Override
    public void buy(MarketInterface market, GameClientInterface gameClient) throws RemoteException {
        market.buyRemoteStarHouse(gameClient, this);
    }
}
