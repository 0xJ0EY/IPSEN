package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.ActionInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.GoodOnSaleInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.notifications.TestNotification;
import server.sources.notifications.TradeNotification;

import java.rmi.RemoteException;

public class AcceptAction implements ActionInterface {

    private GameClientInterface buyer;
    private GoodOnSaleInterface goodOnSale;
    private int bid;

    public AcceptAction(GameClientInterface buyer, GoodOnSaleInterface goodOnSale, int bid){
        this.buyer = buyer;
        this.goodOnSale = goodOnSale;
        this.bid = bid;
    }

    @Override
    public void execute(Server server) throws RemoteException {

        buyer.getPlayer().getPlayerBoard().addGood(goodOnSale.getGood());
        buyer.getPlayer().getPlayerBoard().payCoin(bid);

        goodOnSale.getClient().getPlayer().getPlayerBoard().addCoins(bid);

        buyer.getServer().notifyClients(new TradeNotification(buyer, goodOnSale.getClient()));
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }

}
