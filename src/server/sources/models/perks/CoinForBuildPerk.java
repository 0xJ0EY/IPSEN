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

        if (gameclient.getPlayer().getPlayerBoard().getHasCoinForBuildPerk()){
            gameclient.getPlayer().getPlayerBoard().addCoins(1);
            System.out.println("Coins increased due to coin for build perk");
        }
    }
}
