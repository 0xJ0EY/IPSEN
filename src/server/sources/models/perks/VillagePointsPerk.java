package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsPerk implements Perk {

    private int value;

    public VillagePointsPerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "village_points.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {

    }

    public int getValue() {
        return this.value;
    }

}
