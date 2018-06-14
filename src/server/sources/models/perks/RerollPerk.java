package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

/**
 * Created by robin on 28-5-2018.
 */
public class RerollPerk implements Perk, Refreshable {

    private String perk;
    private int amount;
    private GameClientInterface gameClient;

    //TODO: reroll in explore kunnen gebruiken

    public RerollPerk(String textContent, int amount) {
        this.perk = textContent;
        this.amount = amount;
    }

    @Override
    public String getBackground() {
        return "reroll_perk.png";
    }

    @Override
    public void activateOnObtainedPerk(GameClientInterface gameClient) throws RemoteException {
        this.gameClient = gameClient;

        gameClient.getPlayer().getPlayerBoard().addRerolls(amount);

    }

    @Override
    public void refresh() throws RemoteException {
        this.gameClient.getPlayer().getPlayerBoard().addRerolls(amount);
    }
}
