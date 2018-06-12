package server.sources.models.buildings;

import server.sources.interfaces.BuildingInterface;
import server.sources.models.perks.Harvestable;
import server.sources.models.perks.Perk;
import server.sources.models.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by robin on 24-5-2018.
 */
public class Building implements BuildingInterface {

    protected int cost;
    protected ArrayList<Perk> perks;

    private UUID uuid = UUID.randomUUID();
    protected String background = "";

    /**
     * creates a Building.
     * @param cost
     * @param perks
     * @param background
     * @author Robin Silvério
     */
    public Building(int cost, ArrayList<Perk> perks, String background) {
        this.cost = cost;
        this.perks = perks;
        this.background = background;
    }

    /**
     * returns the cost of the building.
     * @return cost int
     * @throws RemoteException
     * @author Robin Silvério
     */
    public int getCost() throws RemoteException {
        return this.cost;
    }

    /**
     * returns the perks of the building.
     * @return Perks ArrayList
     * @throws RemoteException
     * @author Robin Silvério
     */
    @Override
    public ArrayList<Perk> listPerks() throws RemoteException {
        return this.perks;
    }

    /**
     * returns if the building has a harvestable perk
     * @return harvestable boolean
     * @throws RemoteException
     * @author Robin Silvério
     */
    @Override
    public boolean isHarvestable() throws RemoteException {
        if (this.perks == null || this.perks.size() == 0) return false;

        boolean harvestable = false;

        for (Perk perk : this.listPerks()) {
            if (perk instanceof Harvestable) harvestable = true;
        }

        return harvestable;
    }

    /**
     * Returns the background of the building
     * @return background String
     * @throws RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public String getBackground() throws RemoteException {
        return this.background;
    }

    /**
     * checks if the buildings UUID is equal.
     * @param building
     * @return boolean
     * @throws RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public boolean equals(BuildingInterface building) throws RemoteException {
        return this.uuid.equals(building.getUUID());
    }

    /**
     * returns the UUID of the building.
     * @return UUID
     * @throws RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public UUID getUUID() throws RemoteException {
        return this.uuid;
    }
}
