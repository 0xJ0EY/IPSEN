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

    // TODO: Add the background url in the constructor
    protected String background = "house_0.png";

    public Building(int cost, ArrayList<Perk> perks) {
        this.cost = cost;
        this.perks = perks;
    }

    public int getCost() throws RemoteException {
        return this.cost;
    }

    public ArrayList<Perk> listPerks() throws RemoteException {
        return this.perks;
    }

    @Override
    public boolean isHarvestable() throws RemoteException {
        if (this.perks == null || this.perks.size() == 0) return false;

        boolean harvestable = false;

        for (Perk perk : this.listPerks()) {
            if (perk instanceof Harvestable) harvestable = true;
        }

        return harvestable;
    }

    @Override
    public String getBackground() throws RemoteException {
        return this.background;
    }

    @Override
    public boolean equals(BuildingInterface building) throws RemoteException {
        return this.uuid.equals(building.getUUID());
    }

    @Override
    public UUID getUUID() throws RemoteException {
        return this.uuid;
    }
}
