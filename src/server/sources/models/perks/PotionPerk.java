package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class PotionPerk implements Perk {
    private int value = 1;

    public PotionPerk(){
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "potion_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        gameClient.getPlayer().getPlayerBoard().addPotion();
    }
}
