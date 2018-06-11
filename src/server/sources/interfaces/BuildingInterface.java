package server.sources.interfaces;

import server.sources.models.perks.Perk;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

public interface BuildingInterface extends Serializable, Remote {

    public int getCost() throws RemoteException;

    public ArrayList<Perk> listPerks() throws RemoteException;

    public boolean isHarvestable() throws RemoteException;

    public String getBackground() throws RemoteException;

    public boolean equals(BuildingInterface building) throws RemoteException;

    public UUID getUUID() throws RemoteException;
}
