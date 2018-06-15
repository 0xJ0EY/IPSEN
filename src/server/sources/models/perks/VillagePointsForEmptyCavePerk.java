package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForEmptyCavePerk implements Perk, EndOfGame {

    private int value;
    private GameClientInterface gameClient;

    public VillagePointsForEmptyCavePerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "village_points_for_cave_cards.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public int endOfGamePerk() throws RemoteException {
        int caveCards = gameClient.getPlayer().getPlayerBoard().getCaveCards();

        return this.value * caveCards;
    }
}
