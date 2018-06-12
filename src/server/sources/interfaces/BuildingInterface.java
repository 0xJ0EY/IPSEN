package server.sources.interfaces;

import server.sources.models.perks.Perk;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Remote interface so al buildings can be remote object on the server.
 * @author Joey de Ruiter;
 */
public interface BuildingInterface extends Serializable, Remote {

    /**
     * returns the cost of the building.
     * @return int cost
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    public int getCost() throws RemoteException;

    /**
     * returns the list of perks of the building.
     * @return Perk ArrayList
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    public ArrayList<Perk> listPerks() throws RemoteException;

    /**
     * returns if a building is harvestable or not.
     * @return boolean isHarvestable
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean isHarvestable() throws RemoteException;

    /**
     * returns the background of the building.
     * @return background String
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public String getBackground() throws RemoteException;

    /**
     * checks if the buildings UUID is equal.
     * @param building
     * @return boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean equals(BuildingInterface building) throws RemoteException;

    /**
     * returns the UUID of the building.
     * @return UUID
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public UUID getUUID() throws RemoteException;
}
