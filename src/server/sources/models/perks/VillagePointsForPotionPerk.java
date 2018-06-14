package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

public class VillagePointsForPotionPerk implements Perk, EndOfGame {

    int value;
    GameClientInterface gameClient;

    public VillagePointsForPotionPerk(int value){
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "villager_points_for_potion.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public int endOfGamePerk() throws RemoteException {
        int amount = this.gameClient.getPlayer().getPlayerBoard().getPotions();

        return value * amount;
    }
}
