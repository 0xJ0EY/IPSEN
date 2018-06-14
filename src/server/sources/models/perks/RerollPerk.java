package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class RerollPerk implements Perk {

    private String perk;

    public RerollPerk(String textContent) {
        this.perk = textContent;
    }

    @Override
    public String getBackground() {
        return "reroll_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        
    }
}
