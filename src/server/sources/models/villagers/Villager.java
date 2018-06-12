package server.sources.models.villagers;

import client.source.components.villager.TypeDefaultComponent;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.Dice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Robin Silverio
 */
public class Villager extends UnicastRemoteObject implements VillagerInterface {

    public enum VillagerState {USABLE, TIRED, INJURED}

    protected ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

    protected VillagerState state;

    protected String background;

    protected PlayerBoardInterface playerBoard;

    protected boolean slept = false;

    private UUID uuid = UUID.randomUUID();

    /**
     * creates a villager.
     * @param lanterns ArrayList
     * @param state VillagerSate
     * @param background String
     * @throws RemoteException java.rmi.RemoteException
     */
    public Villager(ArrayList<Lantern> lanterns, VillagerState state, String background) throws RemoteException {
        this.lanterns = lanterns;
        this.state = state;
        this.background = background;
    }

    /**
     * sets the playerboard the villager wil show up on.
     * @param playerBoard PlayerBoardInterface
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public void setPlayerBoard(PlayerBoardInterface playerBoard) throws RemoteException {
        this.playerBoard = playerBoard;
    }

    /**
     * calculates the lanterns.
     * @param dice
     * @return lanterns
     * @throws RemoteException java.rmi.RemoteException
     */
    public int calculateLanters(Dice dice) throws RemoteException {
        int amount = 0;
        for (Lantern lantern : lanterns) {
            if (lantern.getAmount(dice.returnValue()) > 0) {
                amount = lantern.getAmount(dice.returnValue());
            }
        }
        return amount;
    }

    /**
     * returns the state of the villager.
     * @return villagerState
     * @throws RemoteException java.rmi.RemoteException
     */
    public VillagerState getState() throws RemoteException {
        return this.state;
    }

    /**
     * returns if the villager is usable.
     * @return usable boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    public boolean isUsable() throws RemoteException {
        return this.state == VillagerState.USABLE;
    }

    /**
     * returns if the villager can sleep.
     * @return cansleep boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    public boolean canSleep() throws RemoteException {
        return this.state != VillagerState.USABLE && !this.slept;
    }

    /**
     * returns if the villager can use a cider.
     * @return canusecider boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    public boolean canUseCider() throws RemoteException {
        return this.state == VillagerState.TIRED;
    }

    /**
     * returns if the villager can use a potion.
     * @return canUsePotion
     * @throws RemoteException java.rmi.RemoteException
     */
    public boolean canUsePotion() throws RemoteException {
        return this.state == VillagerState.INJURED;
    }

    /**
     * tires the villager.
     * @throws RemoteException java.rmi.RemoteException
     */
    public void tire() throws RemoteException {
        this.state = VillagerState.TIRED;
    }

    /**
     * uses a cider.
     * @throws RemoteException java.rmi.RemoteException
     */
    public void useCider() throws RemoteException {
        if (state != VillagerState.TIRED) return;
        this.state = VillagerState.USABLE;
        this.playerBoard.useCider();
    }

    /**
     * uses a potion.
     * @throws RemoteException java.rmi.RemoteException
     */
    public void usePotion() throws RemoteException {
        if (state != VillagerState.INJURED) return;
        this.state = VillagerState.TIRED;
        this.playerBoard.usePotion();
    }

    /**
     * sleeps the villager.
     * @throws RemoteException java.rmi.RemoteException
     */
    public void sleep() throws RemoteException {
        if (state == VillagerState.USABLE) return;

        // Tired -> usable
        if (state == VillagerState.TIRED) this.state = VillagerState.USABLE;

        // Injured -> Tired
        if (state == VillagerState.INJURED) this.state = VillagerState.TIRED;

        this.slept = true;

        this.playerBoard.useBed();
    }

    /**
     * injures the villager.
     * @throws RemoteException java.rmi.RemoteException
     */
    public void injure() throws RemoteException {
        this.state = VillagerState.INJURED;
    }

    /**
     * returns the background of the villager.
     * @return background String
     * @throws RemoteException java.rmi.RemoteException
     */
    public String getBackground() throws RemoteException {
        return background;
    }

    /**
     * Set some of the local variable to the default beginning of round values.
     *
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public void endOfRound() throws RemoteException {
        this.slept = false;
    }

    /**
     * returns if the villager is allround.
     * @return allround boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    public boolean isAllround() throws RemoteException {
        return isBuilder() && isTrainer();
    }

    /**
     * returns if the villager is a Builder.
     * @return builder boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public boolean isBuilder() throws RemoteException {
        return this instanceof Buildable;
    }

    /**
     * returns if the villager is a trainer.
     * @return trainer boolean
     * @throws RemoteException java.rmi.RemoteException
     */
    @Override
    public boolean isTrainer() throws RemoteException {
        return this instanceof Trainable;
    }

    /**
     * checks if the villager UUID is equal.
     * @param villager
     * @return boolean
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public boolean equals(VillagerInterface villager) throws RemoteException {
        return this.uuid.equals(villager.getUUID());
    }

    /**
     * returns the UUID of the villager.
     * @return UUID
     * @throws RemoteException java.rmi.RemoteException
     * @author Joey de Ruiter
     */
    @Override
    public UUID getUUID() throws RemoteException {
        return this.uuid;
    }
}