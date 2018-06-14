package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class IncomeForGoodsPerk implements Perk {

    private int value;
    private String good;

    public IncomeForGoodsPerk(int value, String good) {
        this.value = value;
        this.good = good;
    }

    @Override
    public String getBackground() {
        return "income_from_goods_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {

    }
}
