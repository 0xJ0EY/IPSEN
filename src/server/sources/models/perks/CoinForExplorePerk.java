package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

public class CoinForExplorePerk implements Perk, ActivateOnObtainedInterface {

    private GameClientInterface gameClient;

    @Override
    public String getBackground() {
        return "coin_for_explore.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public void activateOnObtainedPerk() throws RemoteException {
        gameClient.getPlayer().getPlayerBoard().obtainedCoinForExplorePerk();
    }
}
