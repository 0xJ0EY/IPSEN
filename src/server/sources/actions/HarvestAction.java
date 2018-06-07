package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;

import server.sources.models.buildings.Building;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.models.perks.Harvastable;
import server.sources.models.perks.HarvestableGoodPerk;
import server.sources.models.perks.Perk;
import server.sources.models.perks.ReplenishableGoodPerk;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HarvestAction implements VillagerActionInterface {

    private GameClientInterface target;

    private ArrayList<Building> harvestBuildings = new ArrayList<>();
    private ArrayList<VillagerInterface> selectedVillagers;

    /**
     * geeft de client van de speler die het uitvoert mee
     *
     * @param target
     * @author Jan Douwe
     */
    public HarvestAction(GameClientInterface target){
        this.target = target;

        try {
            selectHarvestable();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void execute(Server server) throws RemoteException {
        for(int i = 0; i < this.selectedVillagers.size(); i++){
            target.getClient().showHarvestSelection(harvestBuildings);

        }

    }

    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.selectedVillagers = villagers;
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }

    /**
     * deze code zorgt dat alle huizen van de speler waarbij de harvest actie kan uitvoeren in een arraylist komen
     *
     * @throws RemoteException
     * @author Jan Douwe
     */
    private void selectHarvestable() throws RemoteException{
        ArrayList<House> house = target.getPlayer().getPlayerBoard().getHouses();

        for (int i = 0; i < house.size(); i++){
            if (house.get(i).getGoodComponent() != null)
            harvestBuildings.add(house.get(i));

        }

        ArrayList<Outpost> outpost = target.getPlayer().getPlayerBoard().getOutposts();

        for (int i = 0; i < outpost.size(); i++){
            if (outpost.get(i).getGoodComponent() != null)
                harvestBuildings.add(outpost.get(i));

        }

//        ArrayList<Perk> tstPerks = new ArrayList<>();
//        tstPerks.add(new HarvestableGoodPerk("AMETHYST"));
//        this.harvestBuildings.add(new Building(1, tstPerks));

    }

}
