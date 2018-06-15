package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsPerk implements Perk, EndOfGame {

    private int value;
    private GameClientInterface gameClient;

    public VillagePointsPerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "village_points.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int endOfGamePerk() throws RemoteException {
        return value;
    }
}
