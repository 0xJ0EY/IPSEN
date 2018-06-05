package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.NotificationInterface;
import server.sources.interfaces.VillagerActionInterface;
import server.sources.models.villagers.Villager;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LaborAction implements VillagerActionInterface {

    private ArrayList<Villager> selectedVillagers;
    private boolean firstLaborAction = true;
    private GameClientInterface target;

    public LaborAction(GameClientInterface target){

        System.out.println("target = " + target);

        this.target = target;
    }

    @Override
    public void execute(Server server) throws RemoteException {
        System.out.println(target.getPlayer().getPlayerBoard().getCoins());

//        for(int i = 0; i < selectedVillagers.size(); i++){
//            selectedVillagers.get(i).tire();
//            firstLaborCider();
//
//            target.getPlayer().getPlayerBoard().addCoins(1);
//        }
//
//        System.out.println(target.getPlayer().getPlayerBoard().getCoins());
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }

    @Override
    public void setSelectedVillagers(ArrayList<Villager> villagers) {
        this.selectedVillagers = villagers;
    }

    private void firstLaborCider(){
        if (firstLaborAction){
            //TODO: toevoegen cider bij player

            firstLaborAction = false;

        }
    }
}
