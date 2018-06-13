package server.sources.interfaces;

import server.sources.models.goods.Good;

import java.io.Serializable;
import java.rmi.Remote;

public interface GoodOnSaleInterface extends Remote, Serializable {

    public GameClientInterface getTarget();

    public Good getGood();

    public GameClientInterface getClient();

}
