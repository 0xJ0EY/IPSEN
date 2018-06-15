package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;
import server.sources.models.GameClient;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class PotionPerk implements Perk, ActivateOnObtainedInterface {
    private int value = 1;
    private GameClientInterface gameClient;

    public PotionPerk(){
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "potion_perk.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public void activateOnObtainedPerk() throws RemoteException {
        gameClient.getPlayer().getPlayerBoard().addPotion();

    }
}
