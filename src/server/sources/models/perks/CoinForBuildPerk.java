package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

public class CoinForBuildPerk implements Perk {

    @Override
    public String getBackground() {
        return "coin_for_build.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameclient) throws RemoteException {
        gameclient.getPlayer().getPlayerBoard().obtainedCoinForBuildPerk();
    }
}
