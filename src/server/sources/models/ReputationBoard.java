package server.sources.models;

import server.sources.controllers.ReputationBoardController;
import server.sources.interfaces.PlayerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

public class ReputationBoard {
    private ReputationBoardController rbc = new ReputationBoardController();
    private ArrayList<PlayerInterface> players = new ArrayList<PlayerInterface>();

    private ArrayList<Integer> reputation = new ArrayList<Integer>();

    public ReputationBoard(ArrayList<PlayerInterface> players){
        this.players = players;
        System.out.println("nieuw rep board");
    }

    public ArrayList<PlayerInterface> sortPlayersByReputation(ArrayList<PlayerInterface> players) throws RemoteException {
        Collections.sort(getReputation(players));
        return players;
    }

    private ArrayList<Integer> getReputation(ArrayList<PlayerInterface> players) throws RemoteException {
        for (PlayerInterface player:players) {
            this.reputation.add(player.getReputation());
        }
        return reputation;
    }
}
