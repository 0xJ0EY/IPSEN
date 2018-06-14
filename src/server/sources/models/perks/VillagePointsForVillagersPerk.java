package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForVillagersPerk implements Perk {

    private int value;

    public VillagePointsForVillagersPerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "village_points_for_villager_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {

    }
}
