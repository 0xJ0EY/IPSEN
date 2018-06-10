package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class CoinPerk implements Perk {

    private int value;

    public CoinPerk(int value) {
        this.value = value;
    }

    @Override
    public String getBackground() {
        return "coin_perk.png";
    }
}
