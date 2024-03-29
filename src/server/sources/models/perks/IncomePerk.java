package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class IncomePerk implements Perk, Refreshable {

    private int value;
    private GameClientInterface gameclient;

    public IncomePerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "income.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameclient = gameClient;
    }

    @Override
    public void refresh() throws RemoteException {
        this.gameclient.getPlayer().getPlayerBoard().addCoins(value);

    }
}
