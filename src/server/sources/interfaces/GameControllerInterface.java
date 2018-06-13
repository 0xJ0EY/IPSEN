package server.sources.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface used as Remote stub for Java RMI,
 * this stub will be registered in a Observer the client knows when it has been updated.
 * @author Joey de Ruiter
 */
public interface GameControllerInterface extends Remote, Serializable {

    /**
     * Return the story object so the StoryAction can generate stories
     *
     * @author Richard Kerkvliet
     * @return StoryControllerInterface
     * @throws RemoteException java.rmi.RemoteException
     */
    public StoryControllerInterface getStories() throws RemoteException;

    /**
     * Return the market interface so we can buy stuff.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException java.rmi.RemoteException
     */
    public MarketInterface getMarket() throws RemoteException;

    /**
     * Return the reputation board, this will keep track of the cider and the reputation.
     *
     * @author Joey de Ruiter
     * @return
     * @throws RemoteException java.rmi.RemoteException
     */
    public ReputationBoardInterface getReputationBoard() throws RemoteException;

    /**
     * Return the server interface
     * @return
     * @throws RemoteException
     */
    public ServerInterface getServer() throws RemoteException;

    public int getCurrentRound() throws RemoteException;

}
