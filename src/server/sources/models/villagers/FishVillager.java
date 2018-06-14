package server.sources.models.villagers;

import client.source.components.villager.TypeBuilderComponent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class FishVillager extends Villager implements Buildable {

    /**
     * creates a FishVillager.
     * @param lanterns
     * @param state
     * @param background
     * @throws RemoteException java.rmi.RemoteException
     */
    public FishVillager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        super(lanterns, state, background);
    }

}
