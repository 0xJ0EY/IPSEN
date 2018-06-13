package client.source.controllers;

import client.source.observers.Observable;
import javafx.scene.Parent;

import java.rmi.RemoteException;

public class LaborRewardController implements ControllerInterface, Observable {
    @Override
    public Parent show() throws RemoteException {
        return null;
    }

    @Override
    public void updateObserver() {

    }
}
