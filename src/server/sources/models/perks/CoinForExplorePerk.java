package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

public class CoinForExplorePerk implements Perk {

    @Override
    public String getBackground() {
        return "coin_for_explore.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        gameClient.getPlayer().getPlayerBoard().obtainedCoinForExplorePerk();
    }
}
