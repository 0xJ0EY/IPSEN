package server.sources.controllers;

import client.source.Client;
import server.sources.interfaces.GameControllerInterface;
import server.sources.interfaces.PlayerInterface;
import server.sources.interfaces.ReputationBoardInterface;
import server.sources.models.goods.Good;
import server.sources.notifications.UpdateReputationBoardNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ReputationBoardController implements ReputationBoardInterface {

    private GameControllerInterface gameController;
    private boolean cider = true;

    public ReputationBoardController(GameController gameController) {
        this.gameController = gameController;
    }

    public boolean hasCider(){
        return this.cider;
    }

    @Override
    public void retrieveCider(PlayerInterface player) throws RemoteException {
        if (!this.hasCider()) return;

        player.getPlayerBoard().addCider();
        this.useCider();
    }

    private void useCider() throws RemoteException {
        this.cider = false;
        this.update();
    }

    public void resetCider() throws RemoteException {
        this.cider = true;
        this.update();
    }

    private void update() throws RemoteException {
        this.gameController.getServer().notifyClients(new UpdateReputationBoardNotification(this));
    }

}
