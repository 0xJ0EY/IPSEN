package client.source.controllers;

import client.source.Client;
import server.sources.controllers.PlayerBoardController;

import java.rmi.RemoteException;

public class AboveController {

    private Client client;
    private PlayerBoardController pbc = new PlayerBoardController();

    public AboveController() throws RemoteException {
    }

    /**
     * For setting a client
     * @param client
     */
    public void registerClient(Client client) {
        this.client = client;
    }

    public void setPbc(PlayerBoardController pbc) {
        this.pbc = pbc;
    }

    public void getVillagers() throws RemoteException {
        pbc.listAvailableVillagers();
    }

    public void getAvailableVillagers() throws RemoteException {
        pbc.listAvailableVillagers();
    }

    public void getHouses() throws RemoteException{
        pbc.getHouses();
    }

    public void getOutposts() throws RemoteException{
        pbc.getOutposts();
    }

    public void getCoins() {
        pbc.getCoins();
    }

    public void getPotions() {
        pbc.getPotions();
    }

    public void getCiders() {
        pbc.getCiders();
    }

}
