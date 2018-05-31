package client.source.controllers;

import client.source.Client;
import server.sources.controllers.PlayerBoardController;

public class AboveController {

    private Client client;
    private PlayerBoardController pbc = new PlayerBoardController();

    /**
     * For setting a client
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    public void setPbc(PlayerBoardController pbc) {
        this.pbc = pbc;
    }

    public void getVillagers() {
        pbc.getVillagers();
    }

    public void getHouses() {
        pbc.getHouses();
    }

    public void getOutposts() {
        pbc.getOutposts();
    }

    public void getCoins() {
        pbc.getCoins();
    }

    public void getBarrels() {
        pbc.getBarrels();
    }

    public void getCiders() {
        pbc.getCiders();
    }

}
