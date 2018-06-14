package server.sources.models.villagers;

import client.source.components.villager.TypeBuilderComponent;
import client.source.components.villager.TypeTrainerComponent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class TrainerVillager extends Villager implements Trainable {

    /**
     * creates a trainervillager.
     * @param lanterns
     * @param state
     * @param background
     * @throws RemoteException java.rmi.RemoteException
     */
    public TrainerVillager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        super(lanterns, state, background);
    }

}
