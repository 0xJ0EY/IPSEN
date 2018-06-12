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
public class MarketOutpost extends Outpost implements BuildingMarketInterface {

    /**
     * creates a MarketOutpost.
     * @param cost
     * @param perks
     * @param background
     * @author Robin Silvério
     */
    public MarketOutpost(int cost, ArrayList<Perk> perks, String background) {
        super(cost, perks, background);
    }

    /**
     * Buys the house and adds it to the player.
     * @param market
     * @param gameClient
     * @return void
     * @throws RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public void buy(MarketInterface market, GameClientInterface gameClient) throws RemoteException {
        market.buyRemoteOutpost(gameClient, this);
    }
}
