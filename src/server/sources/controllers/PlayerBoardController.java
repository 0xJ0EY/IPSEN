package server.sources.controllers;

import server.sources.interfaces.VillagerInterface;
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

    private int ciders = 2;
    private int potions = 2;
    private int coins = 10;

    public PlayerBoardController() throws RemoteException {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

        lanterns.add(new Lantern(3, 2));
        lanterns.add(new Lantern(4, 4));

        villagers.add(new BuilderVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.USABLE));
        villagers.add(new TrainerVillager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.INJURED));
        villagers.add(new Villager((ArrayList<Lantern>) lanterns.clone(), Villager.VillagerState.TIRED));

    }

    public void addCider(int cider) {
        this.ciders += cider;
    }

    public void addPotion(int potion) {
        this.potions += potion;
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

    @Override
    public boolean hasCider() throws RemoteException {
        return this.ciders > 0;
    }

    @Override
    public boolean hasPotion() throws RemoteException {
        return this.potions > 0;
    }

    @Override
    // TODO: This
    public boolean hasBeds() throws RemoteException {
        return true;
    }

    @Override
    public void useCider(VillagerInterface villager) throws RemoteException {
        villager.useCider();
        this.ciders--;
    }

    @Override
    public void usePotion(VillagerInterface villager) throws RemoteException {
        villager.usePotion();
        this.potions--;
    }

    @Override
    public ArrayList<VillagerInterface> listVillagers() throws RemoteException {
        ArrayList<VillagerInterface> villagers = new ArrayList<VillagerInterface>();

        for (Villager villager : this.villagers) {
            villagers.add(villager);
        }

        return villagers;
    }

    @Override
    public ArrayList<VillagerInterface> listAvailableVillagers() throws RemoteException {
        ArrayList<VillagerInterface> usableVillagers = new ArrayList<VillagerInterface>();

        for (Villager villager : this.villagers) {
            if (villager.isUsable()){
                usableVillagers.add(villager);
            }
        }

        return usableVillagers;
    }

    @Override
    public ArrayList<VillagerInterface> listAvailableBuilderVillagers() throws RemoteException {
        ArrayList<VillagerInterface> builders = new ArrayList<VillagerInterface>();

        for (Villager villager : this.villagers) {
            if (villager instanceof Buildable) {
                builders.add(villager);
            }
        }

        return builders;
    }

    @Override
    public ArrayList<VillagerInterface> listAvailableTrainerVillagers() throws RemoteException {
        ArrayList<VillagerInterface> trainers = new ArrayList<VillagerInterface>();

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

    @Override
    public void addCoins(int amount) throws RemoteException {
        if (amount > 0) return;
        this.coins += amount;
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

    public void addCider() throws RemoteException {
        this.ciders += 1;
    }
}
