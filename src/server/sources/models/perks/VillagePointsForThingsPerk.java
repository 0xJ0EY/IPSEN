package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForThingsPerk implements Perk {

    private final int value;
    private Good good;

    public VillagePointsForThingsPerk(int value, Good good) {
        this.value = value;
        this.good = good;
    }

    @Override
    public String getBackground() {
        return null;
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {

    }
}
