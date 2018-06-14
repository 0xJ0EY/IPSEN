package server.sources.interfaces;

import javafx.scene.layout.AnchorPane;
import server.sources.models.Dice;
import server.sources.models.villagers.Lantern;
import server.sources.models.villagers.Villager;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Remote interface so al Villagers can be remote object on the server.
 * @author Joey de Ruiter;
 */
public interface VillagerInterface extends Serializable, Remote {

    /**
     * calculates the rewarded lanterns with the dice.
     * @param dice
     * @return lanterns int
     * @throws RemoteException java.rmi.RemoteException
     * @author Richard Kerkvliet
     */
    public int calculateLanterns(Dice dice) throws RemoteException;

    /**
     * List the lanterns that are contained in this villager
     * @return
     * @throws RemoteException
     */
    public ArrayList<Lantern> listLanterns() throws RemoteException;

    /**
     * sets the playerboard where the villager will be shown.
     * @param playerBoard
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public void setPlayerBoard(PlayerBoardInterface playerBoard) throws RemoteException;

    /**
     * returns the state of the villager.
     * @return villagerState
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public Villager.VillagerState getState() throws RemoteException;

    /**
     * returns if the villager is usable.
     * @return usable boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean isUsable() throws RemoteException;

    /**
     * returns if the villager is tired or injured.
     * @return tired boolean, injured boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean canSleep() throws RemoteException;

    /**
     * returns if the villager is tired.
     * @return tired boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean canUseCider() throws RemoteException;

    /**
     * returns if the villager is injured.
     * @return injured boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean canUsePotion() throws RemoteException;

    /**
     * sets the state of the villager to tired.
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public void tire() throws RemoteException;

    /**
     * uses a cider on the villager.
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public void useCider() throws RemoteException;

    /**
     * uses a potion on the villager.
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public void usePotion() throws RemoteException;

    /**
     * puts the villager to bed.
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public void sleep() throws RemoteException;

    /**
     * injures the villager.
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public void injure() throws RemoteException;

    /**
     * returns the background of the villager.
     * @return background String
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public String getBackground() throws RemoteException;

    /**
     * starts the end of round sequence.
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public void endOfRound() throws RemoteException;

    /**
     * returns if the villager is allround.
     * @return allround boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean isAllround() throws RemoteException;

    /**
     * returns if the villager is builder.
     * @return builder boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean isBuilder() throws RemoteException;

    /**
     * returns if the villager is Trainer.
     * @return trainer boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean isTrainer() throws RemoteException;

    /**
     * checks if the villager UUID is equal.
     * @param villager
     * @return boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public boolean equals(VillagerInterface villager) throws RemoteException;

    /**
     * returns the UUID of the villager.
     * @return UUID
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    public UUID getUUID() throws RemoteException;

}
