package client.source.controllers;

import javafx.scene.Parent;
import java.io.Serializable;
import java.rmi.RemoteException;

public interface ControllerInterface {

    public Parent show() throws RemoteException;

}
