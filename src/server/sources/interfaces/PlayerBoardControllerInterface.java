package server.sources.interfaces;

import server.sources.models.villagers.Villager;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PlayerBoardControllerInterface extends Remote, Serializable {

    public ArrayList<Villager> listAvailableVillagers() throws RemoteException;

    public ArrayList<Villager> listAvailableBuilderVillagers() throws RemoteException;

    public ArrayList<Villager> listAvailableTrainerVillagers() throws RemoteException;

    public Villager getVillager(int index) throws RemoteException;

    public void addVillager(Villager villager) throws RemoteException;

}
