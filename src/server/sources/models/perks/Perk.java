package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by robin on 24-5-2018.
 */
public interface Perk extends Serializable {

    public String getBackground() throws RemoteException;

    public void setGameClient(GameClientInterface gameClient) throws RemoteException;
}
