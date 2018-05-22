package server.sources.interfaces;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

    public void registerClient(GameClientInterface gameClientInterface) throws RemoteException;

    public void unregisterClient(GameClientInterface gameClientInterface) throws RemoteException;

    public void notifyClients(NotificationInterface notification) throws RemoteException;

    public ArrayList<GameClientInterface> listCurrentClients() throws RemoteException;

    public void executeRequest(RequestInterface request) throws RemoteException;

    public void executeAction(ActionInterface action) throws RemoteException;

}
