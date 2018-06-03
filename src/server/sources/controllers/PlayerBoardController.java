package server.sources.controllers;

import server.sources.models.Goods.Good;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.models.villagers.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlayerBoardController extends UnicastRemoteObject implements PlayerBoardControllerInterface {

    private ArrayList<Villager> villagers = new ArrayList<>();
    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<Outpost> outposts = new ArrayList<>();
    private ArrayList<Good> goods = new ArrayList<>();
    private int ciders, potions, coins;

    public PlayerBoardController() throws RemoteException {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

        lanterns.add(new Lantern(3, 2));
        lanterns.add(new Lantern(4, 4));


        villagers.add(new BuilderVillager((ArrayList<Lantern>) lanterns.clone(), false, false));
        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), false, false));
        villagers.add(new Villager((ArrayList<Lantern>) lanterns.clone(), false, false));

    }

    @Override
    public ArrayList<Villager> listAvailableVillagers() throws RemoteException {
        return this.villagers;
    }

    @Override
    public ArrayList<Villager> listAvailableBuilderVillagers() throws RemoteException {
        ArrayList<Villager> builders = new ArrayList<Villager>();

        for (Villager villager : this.villagers) {
            if (villager instanceof Buildable) {
                builders.add(villager);
            }
        }

        return builders;
    }

    @Override
    public ArrayList<Villager> listAvailableTrainerVillagers() throws RemoteException {
        ArrayList<Villager> trainers = new ArrayList<Villager>();

        for (Villager villager : this.villagers) {
            if (villager instanceof Trainable) {
                trainers.add(villager);
            }
        }

        return trainers;
    }

    @Override
    public Villager getVillager(int index) throws RemoteException {
        return villagers.get(index);
    }

    @Override
    public void addVillager(Villager villager) throws RemoteException {
        villagers.add(villager);
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

    public int getPotions() {
        return this.potions;
    }

    public int getCiders() {
        return this.ciders;
    }

    public void addCider(){
        this.ciders = this.ciders + 1;
    }
}
