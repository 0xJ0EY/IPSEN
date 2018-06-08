package server.sources.controllers;

import client.source.Client;
import server.sources.interfaces.GameClientInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.ReputationBoardInterface;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ReputationBoardController implements ReputationBoardInterface {

    private boolean cider = true;

    public boolean hasCider(){
        return this.cider;
    }

    @Override
    public void retrieveCider(PlayerInterface player) throws RemoteException {
        player.getPlayerBoard().addCider();
    }

    public void firstCiderReset(){
        this.cider = true;
    }

}
