package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class CiderPerk implements Perk {

    private int value;

    public CiderPerk(int value){
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "cider_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {

    }
}
