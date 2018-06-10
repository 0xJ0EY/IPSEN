package server.sources.models;

import server.sources.interfaces.AdvancementTrackerInterface;
import server.sources.models.goods.Good;

import java.util.HashMap;
import java.util.Map;

public class AdvancementTracker implements AdvancementTrackerInterface {

    private PlayerBoard playerboard;

    private Map<Good, Integer> tokens = new HashMap<Good, Integer>();

    public AdvancementTracker(PlayerBoard playerboard) {
        this.playerboard = playerboard;
    }

    public void addGood(Good good) {
        Integer integer = tokens.get(good);
        tokens.put(good, ++integer);
    }

    public Map<Good, Integer> getTokens() {
        return this.tokens;
    }

    public int calculatePoints() {
        int points = 0;
        int index = 0;

        for (Integer amount : this.tokens.values()) {
            points += this.getPointsByIndex(index) * amount;
            index++;
        }

        return points;
    }

    public int getPointsByIndex(int index) {
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

    public int calculateCoins() {
        return 4 + (int) Math.round(Math.sqrt(2 * tokens.size()));
    }

}
