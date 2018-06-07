package server.sources.controllers;

import server.sources.models.buildings.Building;
import server.sources.models.goods.*;
import server.sources.models.buildings.House;
import server.sources.models.buildings.Outpost;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.models.villagers.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlayerBoardController extends UnicastRemoteObject implements PlayerBoardControllerInterface {

    private static final long serialVersionUID = 1337L;

    private ArrayList<Villager> villagers = new ArrayList<>();
    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<Outpost> outposts = new ArrayList<>();
    private ArrayList<Good> goods = new ArrayList<>();
    private int ciders, potions;
    private int coins = 20; // This is only for testing coins.

    public PlayerBoardController() throws RemoteException {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

        lanterns.add(new Lantern(3, 2));
        lanterns.add(new Lantern(4, 4));


        villagers.add(new BuilderVillager((ArrayList<Lantern>) lanterns.clone(), false, false));
        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), false, false));
        villagers.add(new Villager((ArrayList<Lantern>) lanterns.clone(), false, false));

    }

    @Override
    public void addCider() throws RemoteException {
        this.ciders += 1;
    }

    @Override
    public void addPotion() throws RemoteException {
        this.potions += 1;
    }

    public void payCoin(int coin){
        this.coins -= coin;
    }

    public void usePotion(int potion){
        this.potions -= potion;
    }

    public void useCider(int cider){
        this.ciders -= cider;
    }

    public void addGood(String good){
        switch (good){
            case "MUSHROOM":
                this.goods.add(new MushroomGood());
                break;
            case "FISH":
                this.goods.add(new FishGood());
                break;
            case "FRUIT":
                this.goods.add(new FruitGood());
                break;
            case "AMETHYST":
                this.goods.add(new AmethystGood());
                break;
            case "PAPER":
                this.goods.add(new PaperGood());
                break;
            case "POT":
                this.goods.add(new PotGood());
                break;
            case "ROPE":
                this.goods.add(new RopeGood());
                break;
            case "ORE":
                this.goods.add(new OreGood());
                break;
            default:
                System.out.println("no good added");
                break;
        }
    }

    /**
     * This is for listing villagers on playerboard when player enters a game environment.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<Villager> listVillagers() throws RemoteException {
        return this.villagers;
    }

    /**
     * This is for listing still available villagers on playerboard after an action is made.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<Villager> listAvailableVillagers() throws RemoteException {
        ArrayList<Villager> usableVillagers = new ArrayList<>();

        for (Villager villager : this.villagers) {
            if (villager.isUseable()){
                usableVillagers.add(villager);
            }
        }

        return usableVillagers;
    }

    /**
     * This is for listing available villagers on playerboard, only to use for build action.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException
     */
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

    /**
     * This is for listing available villagers on playerboard, only to use for train action.
     * @author Jan Douwe Sminia
     * @return
     * @throws RemoteException
     */
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

    /**
     * This is for adding new trained villagers to playerboard.
     * @param villager
     * @throws RemoteException
     */
    @Override
    public void addVillager(Villager villager) throws RemoteException {
        villager.tire();
        villagers.add(villager);
    }

    @Override
    public void addCoins(int amount) throws RemoteException {
        if (amount > 0) return;
        this.coins += amount;
    }

    /**
     * For retrieving houses. Mostly with a purpose to add newly build houses.
     * @return
     */
    public ArrayList<House> getHouses() {
        return this.houses;
    }
    public void addHouse(House house){
        this.houses.add(house);
    }

    public ArrayList<Outpost> getOutposts() {
        return this.outposts;
    }
    public void addOutpost(Outpost outpost){
        this.outposts.add(outpost);
    }

    public ArrayList<Good> getGoods(){
        return this.goods;
    }

    /**
     * Get current amount of coins
     *
     * Khajiit  has wares, if you have coin
     * @return int of current amount
     * @throws RemoteException
     */
    @Override
    public int getCoins() {
        return this.coins;
    }

    public int getPotions() {
        return this.potions;
    }

    public int getCiders() {
        return this.ciders;
    }
}
