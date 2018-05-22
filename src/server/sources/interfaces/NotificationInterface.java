package server.sources.interfaces;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface NotificationInterface extends Serializable {

    public void execute(GameClientInterface gameClient) throws RemoteException;

}
