package server.sources.models.perks;

import java.rmi.RemoteException;

public interface Refreshable extends Perk {

    public void refresh() throws RemoteException;

}
