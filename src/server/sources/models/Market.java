package server.sources.models;

import server.sources.models.buildings.House;
import server.sources.models.buildings.KeyHouse;
import server.sources.models.buildings.Outpost;
import server.sources.models.buildings.StarHouse;
import server.sources.models.villagers.Villager;

import java.util.ArrayList;

public class Market {

    private ArrayList<StarHouse> starHouses = new ArrayList<>();
    private ArrayList<KeyHouse> keyHouses = new ArrayList<>();
    private ArrayList<House> houses = new ArrayList<>();
    private ArrayList<Outpost> Outposts = new ArrayList<>();
    private ArrayList<Villager> villagers = new ArrayList<>();

    //TODO: invullen methods
    public void refreshHouses(Player player){

    }

    public void replenishHouses(){

    }

    public void replenishOutposts(){

    }

    public void replenishVillagers(){

    }

    public void buyHouse(Player player, House house){

    }

    public void buyOutposts(Player player, House house){

    }

    public void buyStarHouse(Player player, House house){

    }

    public void buyKeyHouse(Player player, House house){

    }

    public void buyVillager(Player player, Villager villager){

    }
}
