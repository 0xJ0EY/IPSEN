package server.sources.models;

import server.sources.interfaces.AdvancementTrackerInterface;
import server.sources.models.goods.Good;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdvancementTracker extends UnicastRemoteObject implements AdvancementTrackerInterface {

    private PlayerBoard playerboard;

    private Map<Good, Integer> tokens = new LinkedHashMap<Good, Integer>();

    public AdvancementTracker(PlayerBoard playerboard) throws RemoteException {
        this.playerboard = playerboard;
    }

    public Map.Entry<Good, Integer> getKey(Good good) {

        for (Map.Entry<Good, Integer> entry : this.tokens.entrySet()) {
            if (entry.getKey().sameInstance(good)) return entry;

        }

        // First good of this instance, so make a new entry
        return new AbstractMap.SimpleEntry<>(good, 0);
    }

    public void addGood(Good good) throws RemoteException {
        Map.Entry<Good, Integer> entry = this.getKey(good);

        entry.setValue(entry.getValue() + 1);

        if (tokens.containsKey(entry.getKey())) {
            tokens.replace(entry.getKey(), entry.getValue());
        } else {
            tokens.put(entry.getKey(), entry.getValue());
        }


        System.out.println(this.tokens);
    }

    public Map<Good, Integer> getTokens() throws RemoteException {
        return this.tokens;
    }

    public int calculatePoints() throws RemoteException {
        int points = 0;
        int index = 0;

        for (Integer amount : this.tokens.values()) {
            points += this.getPointsByIndex(index) * amount;
            index++;
        }

        return points;
    }

    public int getPointsByIndex(int index) throws RemoteException {
        switch (index) {
            case 0:
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            default:
                return 0;
        }
    }

    public int calculateCoins() throws RemoteException {
        return 4 + (int) Math.round(Math.sqrt(2 * tokens.size()));
    }

}
