package server.sources.interfaces;

import javafx.scene.layout.AnchorPane;
import server.sources.models.Dice;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VillagerInterface extends Serializable, Remote {

    public int calculateLanters(Dice dice) throws RemoteException;

    public void setPlayerBoard(PlayerBoardInterface playerBoard) throws RemoteException;

    public boolean isUsable() throws RemoteException;

    public boolean canSleep() throws RemoteException;

    public boolean canUseCider() throws RemoteException;

    public boolean canUsePotion() throws RemoteException;

    public void tire() throws RemoteException;

    public void useCider() throws RemoteException;

    public void usePotion() throws RemoteException;

    public void sleep() throws RemoteException;

    public void injure() throws RemoteException;

    public AnchorPane getType() throws RemoteException;

    public String getBackground() throws RemoteException;

    public void reset() throws RemoteException;

}
