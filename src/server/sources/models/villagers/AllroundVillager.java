package server.sources.models.villagers;

import client.source.components.villager.TypeAllroundComponent;
import client.source.components.villager.TypeDefaultComponent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AllroundVillager extends Villager implements Trainable, Buildable {

    /**
     * creates a AllRoundVillager.
     * @param lanterns
     * @param state
     * @param background
     * @throws RemoteException java.rmi.RemoteException
     */
    public AllroundVillager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        super(lanterns, state, background);
    }

    //TODO: implementing build
    public void build() {

    }

    //TODO: implenting train
    public void train() {

    }

}
