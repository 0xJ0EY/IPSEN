package server.sources.interfaces;

import server.sources.Server;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface RequestInterface extends Serializable {

    /**
     * Instructions to be executed on the server for the action
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    public void execute(Server server) throws RemoteException;

}
