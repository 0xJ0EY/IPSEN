package server.sources.interfaces;

import server.sources.Server;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface ActionInterface extends RequestInterface {

    /**
     * Return a notification to update all the clients
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public NotificationInterface update() throws RemoteException;

}
