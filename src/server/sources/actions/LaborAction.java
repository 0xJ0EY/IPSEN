package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.*;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LaborAction implements VillagerActionInterface {

    private ArrayList<VillagerInterface> selectedVillagers;
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

        PlayerBoardInterface playerBoard = target.getPlayer().getPlayerBoard();

        System.out.println(playerBoard.getCoins());

        for(int i = 0; i < selectedVillagers.size(); i++){
            selectedVillagers.get(i).tire();

            this.firstLaborCider();

            playerBoard.addCoins(1);
        }
    }

    @Override
    public NotificationInterface update() throws RemoteException {
        return new TestNotification();
    }

    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.selectedVillagers = villagers;
    }

    private void firstLaborCider() throws RemoteException{
        if(reputationBoard.hasCider()){
            reputationBoard.retrieveCider(target.getPlayer());

        }

    }

}
