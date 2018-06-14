package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForGoodsPerk implements Perk {

    private final int value;
    private Good good;

    public VillagePointsForGoodsPerk(int value, Good good) {
        this.value = value;
        this.good = good;
    }

    @Override
    public String getBackground() {
        return "village_points_for_goods_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {

    }
}
