package server.sources.models.perks;

import server.sources.interfaces.GameClientInterface;

import java.rmi.RemoteException;

public interface ActivateOnObtainedInterface  {
    public void activateOnObtainedPerk() throws RemoteException;
}
