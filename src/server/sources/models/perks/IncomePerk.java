package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class IncomePerk implements Perk {

    private int value;

    public IncomePerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "income_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {

    }
}
