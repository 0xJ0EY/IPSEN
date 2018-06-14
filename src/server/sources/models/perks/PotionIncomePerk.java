package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

public class PotionIncomePerk implements Perk, Refreshable {
    private GameClientInterface gameClient;

    @Override
    public String getBackground() {
        return "potion_income.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public void refresh() throws RemoteException{
        this.gameClient.getPlayer().getPlayerBoard().addPotion();
    }
}
