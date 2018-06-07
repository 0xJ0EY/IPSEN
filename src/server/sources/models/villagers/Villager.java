package server.sources.models.villagers;

import client.source.components.villager.TypeDefaultComponent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import server.sources.interfaces.PlayerBoardControllerInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.Dice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Villager extends UnicastRemoteObject implements VillagerInterface {

    public enum VillagerState { USABLE, TIRED, INJURED }

    protected ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

    protected VillagerState state;

    protected String background;

    protected PlayerBoardControllerInterface playerBoard;

    protected boolean slept = false;

    public Villager(ArrayList<Lantern> lanterns, VillagerState state) throws RemoteException {
        this.lanterns = lanterns;
        this.state = state;

        this.generateRandomBackground();
    }

    @Override
    public void setPlayerBoard(PlayerBoardControllerInterface playerBoard) throws RemoteException {
        this.playerBoard = playerBoard;
    }

    private void generateRandomBackground() throws RemoteException {
        int villager = (int) Math.floor(Math.random() * 5 + 1);
        this.background = "villagerBackground" + villager + ".png";

    }

    public int calculateLanters(Dice dice) throws RemoteException {
        int amount = 0;
        for (Lantern lantern: lanterns) {
            if (lantern.getAmount(dice.returnValue()) > 0){
                amount = lantern.getAmount(dice.returnValue());
            }
        }
        return amount;
    }

    public boolean isUsable() throws RemoteException {
        return this.state == VillagerState.USABLE;
    }

    public boolean canSleep() throws RemoteException {
        return this.state != VillagerState.USABLE && !this.slept;
    }

    public boolean canUseCider() throws RemoteException {
        return this.state == VillagerState.TIRED;
    }

    public boolean canUsePotion() throws RemoteException {
        return this.state == VillagerState.INJURED;
    }

    public void tire() throws RemoteException {
        this.state = VillagerState.TIRED;
    }

    public void useCider() throws RemoteException {
        if (state != VillagerState.TIRED) return;
        this.state = VillagerState.USABLE;
    }

    public void usePotion() throws RemoteException {
        if (state != VillagerState.INJURED) return;
        this.state = VillagerState.TIRED;
    }

    public void sleep() throws RemoteException {
        if (state == VillagerState.USABLE) return;

        // Tired -> usable
        if (state == VillagerState.TIRED) this.state = VillagerState.USABLE;

        // Injured -> Tired
        if (state == VillagerState.INJURED) this.state = VillagerState.TIRED;

        this.slept = true;

        this.playerBoard.useBed();
    }

    public void injure() throws RemoteException {
        this.state = VillagerState.INJURED;
    }

    public AnchorPane getType() throws RemoteException {
        return new TypeDefaultComponent();
    }

    public String getBackground() throws RemoteException {
        return background;
    }

    /**
     * Set some of the local variable to the default beginning of round values.
     * @author Joey de Ruiter
     * @throws RemoteException
     */
    @Override
    public void reset() throws RemoteException {
        this.slept = false;
    }
}
