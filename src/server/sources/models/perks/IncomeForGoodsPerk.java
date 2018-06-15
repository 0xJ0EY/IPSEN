package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 28-5-2018.
 */
public class IncomeForGoodsPerk implements Perk, Refreshable {

    private int value;
    private Good good;
    private GameClientInterface gameClient;

    public IncomeForGoodsPerk(int value, Good good) {
        this.value = value;
        this.good = good;
    }

    @Override
    public String getBackground() {
        return "coin_for_good.png";
    }

    @Override
    public void setGameClient(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;
    }

    @Override
    public void refresh() throws RemoteException {
        int amount = 0;
        ArrayList<Good> goods = this.gameClient.getPlayer().getPlayerBoard().getGoods();

        for (Good good : goods) {
            if (good.sameInstance(this.good)){
                amount++;
            }
        }

        this.gameClient.getPlayer().getPlayerBoard().addCoins(value * amount);

    }
}
