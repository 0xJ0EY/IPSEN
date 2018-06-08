package server.sources.models.villagers;

import client.source.components.villager.TypeDefaultComponent;
import javafx.scene.layout.AnchorPane;
import server.sources.interfaces.PlayerBoardInterface;
import server.sources.interfaces.VillagerInterface;
import server.sources.models.Dice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Villager extends UnicastRemoteObject implements VillagerInterface {

    public enum VillagerState { USABLE, TIRED, INJURED }

    protected ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

    private VillagerState state;

    protected String background;

    public Villager(ArrayList<Lantern> lanterns, VillagerState state) throws RemoteException {
        this.lanterns = lanterns;
        this.state = state;

        this.generateRandomBackground();
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

    public void rest(PlayerBoardInterface playerBoard) throws RemoteException {
        if (state != VillagerState.TIRED) return;
        this.state = VillagerState.USABLE;
    }

    public boolean isUsable() throws RemoteException {
        return this.state == VillagerState.USABLE;
    }

    public boolean canSleep() throws RemoteException {
        return this.state != VillagerState.USABLE;
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

    public void injure() throws RemoteException {
        this.state = VillagerState.INJURED;
    }

    public AnchorPane getType() throws RemoteException {
        return new TypeDefaultComponent();
    }

    public String getBackground() throws RemoteException {
        return background;
    }

}
