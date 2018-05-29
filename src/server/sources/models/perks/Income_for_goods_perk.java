package server.sources.models.perks;

/**
 * Created by robin on 28-5-2018.
 */
public class Income_for_goods_perk implements Perk {

    private int value;
    private String good;

    public Income_for_goods_perk(int value, String good) {
        this.value = value;
        this.good = good;
    }
}
