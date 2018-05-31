package server.sources.interfaces;

import server.sources.exceptions.ServerFullException;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

    public void registerClient(GameClientInterface gameClientInterface, String username) throws RemoteException;

    public void unregisterClient(GameClientInterface gameClientInterface) throws RemoteException;

    public void notifyClients(NotificationInterface notification) throws RemoteException;

    public void executeRequest(RequestInterface request) throws RemoteException;

    public void executeAction(ActionInterface action) throws RemoteException;

    public GameInterface getGame() throws RemoteException;

}
