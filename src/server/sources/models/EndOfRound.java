package server.sources.models;

import server.sources.interfaces.PlayerBoardInterface;
import server.sources.models.buildings.Building;

import java.io.Serializable;
import java.util.ArrayList;

public class EndOfRound implements Serializable {

    private PlayerBoard playerBoard;

    public EndOfRound(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }


    private ArrayList<Building> retrieveAllBuildings() {
        ArrayList<Building> buildings = new ArrayList<>();

        // Retrieve houses


        // Retrieve



        return buildings;
    }


}
