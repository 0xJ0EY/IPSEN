package server.sources.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameInterface extends Remote, Serializable {

    public StoryControllerInterface getStories() throws RemoteException;

    public MarketControllerInterface getMarket() throws RemoteException;

    public ArrayList<PlayerInterface> listCurrentPlayers() throws RemoteException;

}
