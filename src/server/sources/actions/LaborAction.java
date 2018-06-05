package server.sources.actions;

import server.sources.Server;
import server.sources.controllers.GameController;
import server.sources.controllers.ReputationBoardController;
import server.sources.interfaces.*;
import server.sources.models.villagers.Villager;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LaborAction implements VillagerActionInterface {

    private ArrayList<Villager> selectedVillagers;
    private GameClientInterface target;
    private ReputationBoardInterface reputationBoard;

    public LaborAction(GameClientInterface target){
        this.target = target;

        try {
            this.reputationBoard = this.target.getServer().getGameController().getReputationBoard();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Server server) throws RemoteException {
        System.out.println(target.getPlayer().getPlayerBoard().getCoins());

//        ReputationBoardInterface reputationBoard = target.
        PlayerBoardControllerInterface playerBoard = target.getPlayer().getPlayerBoard();


        for(int i = 0; i < selectedVillagers.size(); i++){
            selectedVillagers.get(i).tire();

            firstLaborCider();

            target.getPlayer().getPlayerBoard().addCoins(1);
        }

        System.out.println(target.getPlayer().getPlayerBoard().getCoins());
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }

    @Override
    public void setSelectedVillagers(ArrayList<Villager> villagers) {
        this.selectedVillagers = villagers;
    }

    private void firstLaborCider() throws RemoteException{
        if(reputationBoard.hasCider()){
            reputationBoard.retrieveCider(target.getPlayer());

        }

    }

}
