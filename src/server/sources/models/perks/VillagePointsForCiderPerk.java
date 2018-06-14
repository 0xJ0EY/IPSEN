package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForCiderPerk implements Perk, EndOfGame {

    private final int value;
    private GameClientInterface gameClient;

    public VillagePointsForCiderPerk(int value) {
        this.value = value;

    }

    @Override
    public String getBackground() {
        return "villager_points_for_cider.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public int endOfGamePerk() throws RemoteException {
        int amount = this.gameClient.getPlayer().getPlayerBoard().getCiders();

        return value * amount;
    }
}
