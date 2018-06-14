package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 28-5-2018.
 */
public class VillagePointsForGoodsPerk implements Perk, EndOfGame {

    private final int value;
    private Good good;
    private GameClientInterface gameClient;

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
        this.gameClient = gameClient;
    }

    @Override
    public int endOfGamePerk() throws RemoteException {
        int amount = 0;
        ArrayList<Good> goods = this.gameClient.getPlayer().getPlayerBoard().getGoods();

        for (Good good1 : goods) {
            amount += (int) (Math.random() * 10) + 1;
        }

        return amount;
    }
}
