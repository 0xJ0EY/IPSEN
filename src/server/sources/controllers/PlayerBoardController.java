package server.sources.controllers;

import server.sources.models.Goods.Good;
import server.sources.models.Player;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.models.villagers.Villager;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerBoardController implements Serializable {

    private ArrayList<Villager> villagers = new ArrayList<>();
    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<Outpost> outposts = new ArrayList<>();
    private ArrayList<Good> goods = new ArrayList<>();
    private int ciders, barrels, coins;

    public Villager getVillager(int index) {
        return villagers.get(index);
    }

    public void addVillager(Villager villager) {
        villagers.add(villager);
    }

    public void salary(){

    }

    public ArrayList<Villager> getVillagers() {
        return this.villagers;
    }

    public ArrayList<House> getHouses() {
        return this.houses;
    }

    public ArrayList<Outpost> getOutposts() {
        return this.outposts;
    }

    public ArrayList<Good> getGoods(){
        return this.goods;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getBarrels() {
        return this.barrels;
    }

    public int getCiders() {
        return this.ciders;
    }
}
