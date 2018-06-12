package server.sources.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameControllerInterface extends Remote, Serializable {

    public StoryControllerInterface getStories() throws RemoteException;

    public MarketInterface getMarket() throws RemoteException;

    public ArrayList<PlayerInterface> listCurrentPlayers() throws RemoteException;

    public ReputationBoardInterface getReputationBoard() throws RemoteException;

    public int getCurrentRound() throws RemoteException;

}
