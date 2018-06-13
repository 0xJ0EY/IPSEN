package server.sources.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReputationBoardInterface extends Remote, Serializable {

    public boolean hasCider() throws RemoteException;

    public void retrieveCider(PlayerInterface player) throws RemoteException;

    public void resetCider() throws RemoteException;

}
