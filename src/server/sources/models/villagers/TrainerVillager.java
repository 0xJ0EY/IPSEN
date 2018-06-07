package server.sources.models.villagers;

import client.source.components.villager.TypeBuilderComponent;
import client.source.components.villager.TypeTrainerComponent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class TrainerVillager extends Villager implements Trainable {

    public TrainerVillager(ArrayList<Lantern> lanterns, VillagerState state) throws RemoteException {
        super(lanterns, state);
    }

    @Override
    public void train() {

    }

    @Override
    public AnchorPane getType() {
        return new TypeTrainerComponent();
    }
}
