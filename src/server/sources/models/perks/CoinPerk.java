package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class CoinPerk implements Perk, ActivateOnObtainedInterface {

    private int value;
    private  GameClientInterface gameClient;

    public CoinPerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "coin.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public void activateOnObtainedPerk() throws RemoteException {
        this.gameClient.getPlayer().getPlayerBoard().addCoins(value);
    }
}
