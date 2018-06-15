package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForVillagersPerk implements Perk, EndOfGame {

    private int value;
    private GameClientInterface gameClient;

    public VillagePointsForVillagersPerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "village_points_for_villager_perk.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
     this.gameClient = gameClient;
    }

    @Override
    public int endOfGamePerk() throws RemoteException {
        int amount = this.gameClient.getPlayer().getPlayerBoard().listVillagers().size();

        return value * amount;
    }
}
