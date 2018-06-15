package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class BedPerk implements Perk {

    private int value = 1;

    @Override
    public String getBackground() {
        return "bed_perk.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
    }

}
