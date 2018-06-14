package server.sources.actions;

import server.sources.Server;
import server.sources.interfaces.*;
import server.sources.notifications.LaborRewardNotification;
import server.sources.notifications.RewardScreenNotification;
import server.sources.notifications.TestNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class will be called when the player chooses the labor action on his turn.
 *
 * @author Jan Douwe Sminia.
 */

public class LaborAction implements VillagerActionInterface {

    private ArrayList<VillagerInterface> selectedVillagers;
    private GameClientInterface target;
    private ReputationBoardInterface reputationBoard;

    /**
     * This constructor gets the reputation board from the server.
     *
     * @param target
     */
    public LaborAction(GameClientInterface target){
        this.target = target;

        try {
            this.reputationBoard = this.target.getServer().getGameController().getReputationBoard();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will be activated this class is called.
     * It gives one coin to the player for each villager selected.
     *
     * @param server
     * @throws RemoteException
     */
    @Override
    public void execute(Server server) throws RemoteException {

        PlayerBoardInterface playerBoard = target.getPlayer().getPlayerBoard();

        System.out.println(playerBoard.getCoins());

        for(int i = 0; i < selectedVillagers.size(); i++){

            this.firstLaborCider();

            playerBoard.addCoins(1);
        }
    }

    /**
     * This method allows the other players to see what is happening.
     *
     * @return.
     * @throws RemoteException
     */
    @Override
    public NotificationInterface update() throws RemoteException {
        return new LaborRewardNotification(this.selectedVillagers);
    }

    /**
     * @param villagers
     */
    @Override
    public void setSelectedVillagers(ArrayList<VillagerInterface> villagers) {
        this.selectedVillagers = villagers;
    }

    /**
     * This method checks the reputationboard if the first cider has been taken.
     * If it is still avaiable than the player get one cider.
     */
    private void firstLaborCider() throws RemoteException{
        if(reputationBoard.hasCider()){
            reputationBoard.retrieveCider(target.getPlayer());

        }

    }

}
