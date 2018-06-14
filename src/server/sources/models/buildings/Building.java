package server.sources.models.buildings;

import server.sources.interfaces.BuildingInterface;
import server.sources.interfaces.GameClientInterface;
import server.sources.models.perks.Harvestable;
import server.sources.models.perks.Perk;
import server.sources.models.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * House
 */
public class Building extends UnicastRemoteObject implements BuildingInterface {

    protected int cost;
    protected ArrayList<Perk> perks;

    private UUID uuid = UUID.randomUUID();
    protected String background = "";

    /**
     * creates a Building.
     * @param cost
     * @param perks
     * @param background
     * @author Robin Silverio
     */
    public Building(int cost, ArrayList<Perk> perks, String background) throws RemoteException {
        this.cost = cost;
        this.perks = perks;
        this.background = background;
    }

    /**
     * returns the cost of the building.
     * @return cost int
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    public int getCost() throws RemoteException {
        return this.cost;
    }

    /**
     * returns the perks of the building.
     * @return Perks ArrayList
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @Override
    public ArrayList<Perk> listPerks() throws RemoteException {
        return this.perks;
    }

    /**
     * returns if the building has a harvestable perk
     * @return harvestable boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Robin Silverio
     */
    @Override
    public boolean isHarvestable() throws RemoteException {
        if (this.perks == null || this.perks.size() == 0) return false;

        for (Perk perk : this.listPerks()) {
            if (perk instanceof Harvestable && ((Harvestable) perk).canHarvest()) return true;
        }

        return true;
    }

    /**
     * Harvest the first possible perk(should only be 1 perk per harvestable building).
     * (This will only be executed when isHarbestable() is true)
     *
     * The reason for this method here is that the perks are local object and not remote instances like Buildings.
     * And Java RMI doesnt update local (serializable) objects with the server.
     *
     * @throws RemoteException
     * @author Jan Douwe
     */
    @Override
    public void harvest(GameClientInterface gameClient) throws RemoteException {
        if (!isHarvestable()) return;

        for (int i = 0; i < this.perks.size(); i++) {
            Perk perk = this.perks.get(i);

            if (perk instanceof Harvestable && ((Harvestable) perk).canHarvest()) {
                Harvestable harvestablePerk = (Harvestable) perk;

                gameClient.getPlayer().getPlayerBoard().addGood(harvestablePerk.getGood());
                harvestablePerk.harvest();

                this.perks.set(i, harvestablePerk);
            }
        }
    }

    /**
     * Returns the background of the building
     * @return background String
     * @throws RemoteException java.rmi.RemoteException
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
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public boolean equals(BuildingInterface building) throws RemoteException {
        return this.uuid.equals(building.getUUID());
    }

    /**
     * returns the UUID of the building.
     * @return UUID
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public UUID getUUID() throws RemoteException {
        return this.uuid;
    }
}
