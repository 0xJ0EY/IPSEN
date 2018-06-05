package server.sources.controllers;

import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.ReputationBoardInterface;

import java.rmi.RemoteException;

public class ReputationBoardController implements ReputationBoardInterface {

    private boolean cider = true;

    public boolean hasCider(){
        return this.cider;
    }

    @Override
    public void retrieveCider(PlayerInterface player) throws RemoteException {
        player.getPlayerBoard().addCider();
    }
}
